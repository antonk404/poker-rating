package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Tournament;

import java.util.Optional;
import java.util.UUID;

public interface TournamentRepositoryPort {
    Optional<Tournament> findById(UUID id);
    Tournament save(Tournament tournament);
}
