package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.constant.SeasonConstants;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import com.antonk404.poker_rating.domain.port.in.CreateSeasonUseCase;
import com.antonk404.poker_rating.domain.port.out.SeasonRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CreateSeasonUseCaseImpl implements CreateSeasonUseCase {

    private final SeasonRepositoryPort seasonPort;

    public CreateSeasonUseCaseImpl(SeasonRepositoryPort seasonPort) {
        this.seasonPort = seasonPort;
    }

    @Override
    @Transactional
    public Season createSeason(Integer year, Integer month, Integer topResultsCount,
                               SeasonStatus status, LocalDateTime startedAt, LocalDateTime endedAt) {
        int resolvedTopResults = topResultsCount != null ? topResultsCount : SeasonConstants.DEFAULT_TOP_RESULTS_COUNT;
        return seasonPort.save(new Season(null, year, month, resolvedTopResults, status, startedAt, endedAt));
    }
}
