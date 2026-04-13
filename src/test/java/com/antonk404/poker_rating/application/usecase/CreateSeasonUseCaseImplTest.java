package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.constant.SeasonConstants;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import com.antonk404.poker_rating.domain.port.out.SeasonRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateSeasonUseCaseImplTest {

    @Mock private SeasonRepositoryPort seasonPort;
    @InjectMocks private CreateSeasonUseCaseImpl useCase;

    @Test
    void createSeason_withNullTopResultsCount_usesDefault() {
        when(seasonPort.save(any())).thenAnswer(inv -> {
            Season s = inv.getArgument(0);
            return new Season(UUID.randomUUID(), s.getYear(), s.getMonth(),
                    s.getTopResultsCount(), s.getStatus(), s.getStartedAt(), s.getEndedAt());
        });

        useCase.createSeason(2026, 4, null, SeasonStatus.ACTIVE, LocalDateTime.now(), null);

        ArgumentCaptor<Season> captor = ArgumentCaptor.forClass(Season.class);
        verify(seasonPort).save(captor.capture());

        assertThat(captor.getValue().getTopResultsCount())
                .isEqualTo(SeasonConstants.DEFAULT_TOP_RESULTS_COUNT);
    }

    @Test
    void createSeason_withExplicitTopResultsCount_usesProvidedValue() {
        when(seasonPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        useCase.createSeason(2026, 4, 8, SeasonStatus.ACTIVE, LocalDateTime.now(), null);

        ArgumentCaptor<Season> captor = ArgumentCaptor.forClass(Season.class);
        verify(seasonPort).save(captor.capture());

        assertThat(captor.getValue().getTopResultsCount()).isEqualTo(8);
    }
}
