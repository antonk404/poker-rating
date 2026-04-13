package com.antonk404.poker_rating.domain.port.in;

import com.antonk404.poker_rating.domain.model.TournamentParticipant;

import java.util.UUID;

public interface RegisterParticipantUseCase {
    TournamentParticipant registerParticipant(UUID tournamentId, UUID playerId);
}
