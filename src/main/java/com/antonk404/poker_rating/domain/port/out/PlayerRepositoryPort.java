package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepositoryPort {
    Optional<Player> findById(UUID id);
    Player save(Player player);
}
