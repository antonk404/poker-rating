package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentParticipantRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterParticipantUseCaseImplTest {

    @Mock private TournamentRepositoryPort tournamentPort;
    @Mock private PlayerRepositoryPort playerPort;
    @Mock private TournamentParticipantRepositoryPort participantPort;
    @InjectMocks private RegisterParticipantUseCaseImpl useCase;

    @Test
    void registerParticipant_tournamentNotFound_throwsEntityNotFoundException() {
        UUID tournamentId = UUID.randomUUID();
        when(tournamentPort.findById(tournamentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.registerParticipant(tournamentId, UUID.randomUUID()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void registerParticipant_playerNotFound_throwsEntityNotFoundException() {
        Tournament tournament = tournament();
        UUID playerId = UUID.randomUUID();

        when(tournamentPort.findById(tournament.getId())).thenReturn(Optional.of(tournament));
        when(playerPort.findById(playerId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.registerParticipant(tournament.getId(), playerId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void registerParticipant_bothExist_savesParticipantWithNullPosition() {
        Tournament tournament = tournament();
        Player player = new Player(UUID.randomUUID(), "anton", 0, 0);

        when(tournamentPort.findById(tournament.getId())).thenReturn(Optional.of(tournament));
        when(playerPort.findById(player.getId())).thenReturn(Optional.of(player));
        when(participantPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var result = useCase.registerParticipant(tournament.getId(), player.getId());

        assertThat(result.getPlayer()).isEqualTo(player);
        assertThat(result.getTournament()).isEqualTo(tournament);
        assertThat(result.getPosition()).isNull();
        assertThat(result.getRegPoints()).isZero();
        assertThat(result.getProPoints()).isZero();
    }

    private Tournament tournament() {
        Season season = new Season(UUID.randomUUID(), 2026, 4, 5,
                SeasonStatus.ACTIVE, LocalDateTime.now(), null);
        return new Tournament(UUID.randomUUID(), season, "Test", TournamentType.REGULAR,
                10, null, LocalDateTime.now(), null);
    }
}
