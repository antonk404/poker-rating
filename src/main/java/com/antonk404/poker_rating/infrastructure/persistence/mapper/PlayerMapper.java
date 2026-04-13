package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.infrastructure.persistence.entity.PlayerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toDomain(PlayerJpaEntity entity) {
        return new Player(entity.getId(), entity.getUsername(), entity.getRating(), entity.getProRating());
    }

    public PlayerJpaEntity toJpaEntity(Player domain) {
        PlayerJpaEntity entity = new PlayerJpaEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setRating(domain.getRating());
        entity.setProRating(domain.getProRating());
        return entity;
    }
}
