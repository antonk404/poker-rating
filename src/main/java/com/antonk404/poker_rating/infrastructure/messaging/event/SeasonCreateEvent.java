package com.antonk404.poker_rating.infrastructure.messaging.event;

import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;

import java.time.LocalDateTime;

public record SeasonCreateEvent(
        Integer year,
        Integer month,
        Integer topResultsCount,
        SeasonStatus status,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {}
