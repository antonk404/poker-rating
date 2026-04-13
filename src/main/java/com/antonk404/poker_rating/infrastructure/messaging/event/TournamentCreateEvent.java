package com.antonk404.poker_rating.infrastructure.messaging.event;

import com.antonk404.poker_rating.domain.model.enums.TournamentType;

import java.time.LocalDateTime;
import java.util.UUID;

public record TournamentCreateEvent(
        UUID seasonId,
        String name,
        TournamentType type,
        Integer playerCount,
        Integer bountyBase,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {}
