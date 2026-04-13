package com.antonk404.poker_rating.domain.port.in;

import java.util.UUID;

public interface ScoreTournamentUseCase {
    void scoreTournament(UUID tournamentId);
}
