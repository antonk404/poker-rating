package com.antonk404.poker_rating.domain.port.in;

import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.enums.TournamentType;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateTournamentUseCase {
    Tournament createTournament(UUID seasonId, String name, TournamentType type,
                                Integer playerCount, Integer bountyBase,
                                LocalDateTime startedAt, LocalDateTime endedAt);
}
