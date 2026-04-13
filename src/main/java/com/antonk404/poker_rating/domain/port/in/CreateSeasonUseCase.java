package com.antonk404.poker_rating.domain.port.in;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;

import java.time.LocalDateTime;

public interface CreateSeasonUseCase {
    Season createSeason(Integer year, Integer month, Integer topResultsCount,
                        SeasonStatus status, LocalDateTime startedAt, LocalDateTime endedAt);
}
