package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.PlayerMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.PlayerJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PlayerPersistenceAdapter implements PlayerRepositoryPort {

    private final PlayerJpaRepository repository;
    private final PlayerMapper mapper;

    public PlayerPersistenceAdapter(PlayerJpaRepository repository, PlayerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Player save(Player player) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(player)));
    }
}
