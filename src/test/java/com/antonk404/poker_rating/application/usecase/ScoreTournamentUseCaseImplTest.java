package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.*;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import com.antonk404.poker_rating.domain.port.out.*;
import com.antonk404.poker_rating.domain.service.RatingCalculationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScoreTournamentUseCaseImplTest {

    @Mock private TournamentRepositoryPort tournamentPort;
    @Mock private TournamentParticipantRepositoryPort participantPort;
    @Mock private KnockoutRepositoryPort knockoutPort;
    @Mock private SeasonRatingRepositoryPort seasonRatingPort;
    @Mock private PlayerRepositoryPort playerPort;

    private ScoreTournamentUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        useCase = new ScoreTournamentUseCaseImpl(
                tournamentPort, participantPort, knockoutPort,
                seasonRatingPort, playerPort, new RatingCalculationService()
        );
    }

    @Test
    void scoreTournament_tournamentNotFound_throwsEntityNotFoundException() {
        UUID id = UUID.randomUUID();
        when(tournamentPort.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.scoreTournament(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void scoreTournament_regularTournament_calculatesPointsCorrectly() {
        Season season = season(5);
        Tournament tournament = tournament(season, TournamentType.REGULAR, 10, null);

        Player player1 = player(0);
        Player player2 = player(0);

        TournamentParticipant p1 = participant(tournament, player1, 1);
        TournamentParticipant p2 = participant(tournament, player2, 10); // last place

        when(tournamentPort.findById(tournament.getId())).thenReturn(Optional.of(tournament));
        when(participantPort.findByTournament(tournament)).thenReturn(List.of(p1, p2));
        when(participantPort.findByPlayerAndSeason(player1, season)).thenReturn(List.of(p1));
        when(participantPort.findByPlayerAndSeason(player2, season)).thenReturn(List.of(p2));
        when(seasonRatingPort.findBySeasonAndPlayer(any(), any())).thenReturn(Optional.empty());
        when(playerPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        useCase.scoreTournament(tournament.getId());

        // 1st place из 10: regPoints=293, proPoints=1661
        assertThat(p1.getRegPoints()).isEqualTo(293);
        assertThat(p1.getProPoints()).isEqualTo(1661);

        // последнее место: 0 очков
        assertThat(p2.getRegPoints()).isZero();
        assertThat(p2.getProPoints()).isZero();
    }

    @Test
    void scoreTournament_regularTournament_updatesSeasonRatingAndPlayerRating() {
        Season season = season(5);
        Tournament tournament = tournament(season, TournamentType.REGULAR, 10, null);

        Player player = player(0);
        TournamentParticipant p = participant(tournament, player, 1);

        when(tournamentPort.findById(tournament.getId())).thenReturn(Optional.of(tournament));
        when(participantPort.findByTournament(tournament)).thenReturn(List.of(p));
        when(participantPort.findByPlayerAndSeason(player, season)).thenReturn(List.of(p));
        when(seasonRatingPort.findBySeasonAndPlayer(season, player)).thenReturn(Optional.empty());
        when(playerPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        useCase.scoreTournament(tournament.getId());

        ArgumentCaptor<SeasonRating> ratingCaptor = ArgumentCaptor.forClass(SeasonRating.class);
        verify(seasonRatingPort).save(ratingCaptor.capture());
        SeasonRating saved = ratingCaptor.getValue();

        assertThat(saved.getTotalReg()).isEqualTo(293);
        assertThat(saved.getTotalPro()).isEqualTo(1661);
        assertThat(player.getRating()).isEqualTo(293);
        assertThat(player.getProRating()).isEqualTo(1661);
    }

    @Test
    void scoreTournament_bountyTournament_appliesWeightedFormula() {
        Season season = season(5);
        Tournament tournament = tournament(season, TournamentType.BOUNTY, 10, 100);

        Player killer = player(0);
        Player victim = player(0);

        TournamentParticipant kp = participant(tournament, killer, 1);
        TournamentParticipant vp = participant(tournament, victim, 10);

        Knockout ko = new Knockout(UUID.randomUUID(), tournament, killer, victim, 0);

        when(tournamentPort.findById(tournament.getId())).thenReturn(Optional.of(tournament));
        when(participantPort.findByTournament(tournament)).thenReturn(List.of(kp, vp));
        when(knockoutPort.findByTournament(tournament)).thenReturn(List.of(ko));
        when(participantPort.findByPlayerAndSeason(any(), any())).thenReturn(List.of());
        when(seasonRatingPort.findBySeasonAndPlayer(any(), any())).thenReturn(Optional.empty());
        when(playerPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        useCase.scoreTournament(tournament.getId());

        // avgPro = 0 → bountyScore = 100 * 1.0 = 100
        // killer: regPlacement=293, proPlacement=1661, bounty=100
        // regPoints = round(293 * 0.6 + 100 * 0.4) = round(175.8 + 40) = 216
        // proPoints = round(1661 * 0.6 + 100 * 0.4) = round(996.6 + 40) = 1037
        assertThat(kp.getBountyPoints()).isEqualTo(100);
        assertThat(kp.getRegPoints()).isEqualTo(216);
        assertThat(kp.getProPoints()).isEqualTo(1037);

        // victim: last place → 0 placement points, 0 bounty
        assertThat(vp.getRegPoints()).isZero();
        assertThat(vp.getProPoints()).isZero();
    }

    @Test
    void scoreTournament_proRating_sumsOnlyTopKResults() {
        Season season = season(2); // topResultsCount = 2

        Tournament t1 = tournament(season, TournamentType.REGULAR, 10, null);
        Tournament t2 = tournament(season, TournamentType.REGULAR, 10, null);
        Tournament t3 = tournament(season, TournamentType.REGULAR, 10, null);

        Player player = player(0);

        // Три результата: proPoints = 1661, 500, 100 → топ-2 = 1661+500 = 2161
        TournamentParticipant r1 = participantWithPoints(t1, player, 1661, 0);
        TournamentParticipant r2 = participantWithPoints(t2, player, 500, 0);
        TournamentParticipant r3 = participantWithPoints(t3, player, 100, 0);

        TournamentParticipant current = participant(t3, player, 1);

        when(tournamentPort.findById(t3.getId())).thenReturn(Optional.of(t3));
        when(participantPort.findByTournament(t3)).thenReturn(List.of(current));
        when(participantPort.findByPlayerAndSeason(player, season)).thenReturn(List.of(r1, r2, r3));
        when(seasonRatingPort.findBySeasonAndPlayer(season, player)).thenReturn(Optional.empty());
        when(playerPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        useCase.scoreTournament(t3.getId());

        ArgumentCaptor<SeasonRating> captor = ArgumentCaptor.forClass(SeasonRating.class);
        verify(seasonRatingPort).save(captor.capture());
        assertThat(captor.getValue().getTotalPro()).isEqualTo(2161);
    }

    // --- helpers ---

    private Season season(int topResultsCount) {
        return new Season(UUID.randomUUID(), 2026, 4, topResultsCount,
                SeasonStatus.ACTIVE, LocalDateTime.now(), null);
    }

    private Tournament tournament(Season season, TournamentType type, int playerCount, Integer bountyBase) {
        return new Tournament(UUID.randomUUID(), season, "Test", type, playerCount, bountyBase,
                LocalDateTime.now(), null);
    }

    private Player player(int proRating) {
        return new Player(UUID.randomUUID(), "player", 0, proRating);
    }

    private TournamentParticipant participant(Tournament tournament, Player player, int position) {
        return new TournamentParticipant(UUID.randomUUID(), tournament, player, position, null, 0, 0, 0);
    }

    private TournamentParticipant participantWithPoints(Tournament tournament, Player player, int proPoints, int regPoints) {
        return new TournamentParticipant(UUID.randomUUID(), tournament, player, 1, null, regPoints, proPoints, 0);
    }
}
