package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.*;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import com.antonk404.poker_rating.domain.port.in.ScoreTournamentUseCase;
import com.antonk404.poker_rating.domain.port.out.*;
import com.antonk404.poker_rating.domain.service.RatingCalculationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ScoreTournamentUseCaseImpl implements ScoreTournamentUseCase {

    private final TournamentRepositoryPort tournamentPort;
    private final TournamentParticipantRepositoryPort participantPort;
    private final KnockoutRepositoryPort knockoutPort;
    private final SeasonRatingRepositoryPort seasonRatingPort;
    private final PlayerRepositoryPort playerPort;
    private final RatingCalculationService ratingCalc;

    public ScoreTournamentUseCaseImpl(
            TournamentRepositoryPort tournamentPort,
            TournamentParticipantRepositoryPort participantPort,
            KnockoutRepositoryPort knockoutPort,
            SeasonRatingRepositoryPort seasonRatingPort,
            PlayerRepositoryPort playerPort,
            RatingCalculationService ratingCalc) {
        this.tournamentPort = tournamentPort;
        this.participantPort = participantPort;
        this.knockoutPort = knockoutPort;
        this.seasonRatingPort = seasonRatingPort;
        this.playerPort = playerPort;
        this.ratingCalc = ratingCalc;
    }

    @Override
    @Transactional
    public void scoreTournament(UUID tournamentId) {
        Tournament tournament = tournamentPort.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + tournamentId));

        List<TournamentParticipant> participants = participantPort.findByTournament(tournament);
        int totalPlayers = tournament.getPlayerCount();

        if (tournament.getType() == TournamentType.BOUNTY) {
            scoreBountyTournament(tournament, participants, totalPlayers);
        } else {
            scoreRegularTournament(participants, totalPlayers);
        }

        participantPort.saveAll(participants);

        Season season = tournament.getSeason();
        for (TournamentParticipant p : participants) {
            updateSeasonRating(season, p.getPlayer());
        }
    }

    private void scoreRegularTournament(List<TournamentParticipant> participants, int totalPlayers) {
        for (TournamentParticipant p : participants) {
            p.setRegPoints(ratingCalc.calcRegPoints(p.getPosition(), totalPlayers));
            p.setProPoints(ratingCalc.calcProPoints(p.getPosition(), totalPlayers));
        }
    }

    private void scoreBountyTournament(Tournament tournament, List<TournamentParticipant> participants, int totalPlayers) {
        List<Knockout> knockouts = knockoutPort.findByTournament(tournament);

        double avgPro = participants.stream()
                .mapToInt(p -> p.getPlayer().getProRating())
                .average()
                .orElse(0.0);

        Map<UUID, Integer> bountyByKiller = new HashMap<>();
        for (Knockout ko : knockouts) {
            int score = ratingCalc.calcBountyScore(
                    tournament.getBountyBase(),
                    ko.getVictim().getProRating(),
                    avgPro
            );
            bountyByKiller.merge(ko.getKiller().getId(), score, Integer::sum);
        }

        for (TournamentParticipant p : participants) {
            int regPlacement = ratingCalc.calcRegPoints(p.getPosition(), totalPlayers);
            int proPlacement = ratingCalc.calcProPoints(p.getPosition(), totalPlayers);
            int bountyTotal = bountyByKiller.getOrDefault(p.getPlayer().getId(), 0);

            p.setBountyPoints(bountyTotal);
            p.setRegPoints((int) Math.round(regPlacement * 0.6 + bountyTotal * 0.4));
            p.setProPoints((int) Math.round(proPlacement * 0.6 + bountyTotal * 0.4));
        }
    }

    private void updateSeasonRating(Season season, Player player) {
        List<TournamentParticipant> allResults = participantPort.findByPlayerAndSeason(player, season);

        int totalReg = allResults.stream().mapToInt(TournamentParticipant::getRegPoints).sum();

        int k = season.getTopResultsCount();
        int totalPro = allResults.stream()
                .map(TournamentParticipant::getProPoints)
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .mapToInt(Integer::intValue)
                .sum();

        SeasonRating seasonRating = seasonRatingPort
                .findBySeasonAndPlayer(season, player)
                .orElseGet(() -> new SeasonRating(null, season, player, 0, 0));
        seasonRating.setTotalReg(totalReg);
        seasonRating.setTotalPro(totalPro);
        seasonRatingPort.save(seasonRating);

        player.setRating(totalReg);
        player.setProRating(totalPro);
        playerPort.save(player);
    }
}
