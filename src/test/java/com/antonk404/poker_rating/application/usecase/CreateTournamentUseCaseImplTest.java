package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import com.antonk404.poker_rating.domain.port.out.SeasonRepositoryPort;
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
class CreateTournamentUseCaseImplTest {

    @Mock private SeasonRepositoryPort seasonPort;
    @Mock private TournamentRepositoryPort tournamentPort;
    @InjectMocks private CreateTournamentUseCaseImpl useCase;

    @Test
    void createTournament_seasonNotFound_throwsEntityNotFoundException() {
        UUID seasonId = UUID.randomUUID();
        when(seasonPort.findById(seasonId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.createTournament(
                seasonId, "Final", TournamentType.REGULAR, 10, null,
                LocalDateTime.now(), null))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createTournament_seasonExists_savesTournament() {
        Season season = new Season(UUID.randomUUID(), 2026, 4, 5,
                SeasonStatus.ACTIVE, LocalDateTime.now(), null);

        when(seasonPort.findById(season.getId())).thenReturn(Optional.of(season));
        when(tournamentPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var result = useCase.createTournament(
                season.getId(), "Final", TournamentType.REGULAR, 10, null,
                LocalDateTime.now(), null);

        assertThat(result.getSeason()).isEqualTo(season);
        assertThat(result.getName()).isEqualTo("Final");
    }
}
