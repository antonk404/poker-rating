package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.port.in.CreatePlayerUseCase;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreatePlayerUseCaseImpl implements CreatePlayerUseCase {

    private final PlayerRepositoryPort playerPort;

    public CreatePlayerUseCaseImpl(PlayerRepositoryPort playerPort) {
        this.playerPort = playerPort;
    }

    @Override
    @Transactional
    public Player createPlayer(String username) {
        return playerPort.save(new Player(null, username, 0, 0));
    }
}
