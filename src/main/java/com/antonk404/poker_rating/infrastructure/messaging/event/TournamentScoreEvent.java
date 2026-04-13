package com.antonk404.poker_rating.infrastructure.messaging.event;

import java.util.UUID;

public record TournamentScoreEvent(UUID tournamentId) {}
