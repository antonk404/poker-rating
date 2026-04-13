package com.antonk404.poker_rating.domain.port.in;

import com.antonk404.poker_rating.domain.model.Player;

public interface CreatePlayerUseCase {
    Player createPlayer(String username);
}
