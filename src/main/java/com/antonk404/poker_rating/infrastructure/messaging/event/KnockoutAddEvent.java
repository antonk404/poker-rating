package com.antonk404.poker_rating.infrastructure.messaging.event;

import java.util.UUID;

public record KnockoutAddEvent(UUID tournamentId, UUID killerId, UUID victimId, int bountyPoints) {}
