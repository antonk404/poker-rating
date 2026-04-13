package com.antonk404.poker_rating.infrastructure.messaging.event;

import java.util.UUID;

public record ParticipantRegisterEvent(UUID tournamentId, UUID playerId) {}
