package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import com.antonk404.poker_rating.domain.port.in.CreateTournamentUseCase;
import com.antonk404.poker_rating.domain.port.out.SeasonRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateTournamentUseCaseImpl implements CreateTournamentUseCase {

    private final SeasonRepositoryPort seasonPort;
    private final TournamentRepositoryPort tournamentPort;

    public CreateTournamentUseCaseImpl(SeasonRepositoryPort seasonPort, TournamentRepositoryPort tournamentPort) {
        this.seasonPort = seasonPort;
        this.tournamentPort = tournamentPort;
    }

    @Override
    @Transactional
    public Tournament createTournament(UUID seasonId, String name, TournamentType type,
                                       Integer playerCount, Integer bountyBase,
                                       LocalDateTime startedAt, LocalDateTime endedAt) {
        Season season = seasonPort.findById(seasonId)
                .orElseThrow(() -> new EntityNotFoundException("Season not found: " + seasonId));
        return tournamentPort.save(new Tournament(null, season, name, type, playerCount, bountyBase, startedAt, endedAt));
    }
}
