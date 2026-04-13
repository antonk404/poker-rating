package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.SeasonRating;
import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonRatingJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SeasonRatingMapper {

    private final SeasonMapper seasonMapper;
    private final PlayerMapper playerMapper;

    public SeasonRatingMapper(SeasonMapper seasonMapper, PlayerMapper playerMapper) {
        this.seasonMapper = seasonMapper;
        this.playerMapper = playerMapper;
    }

    public SeasonRating toDomain(SeasonRatingJpaEntity entity) {
        return new SeasonRating(
                entity.getId(),
                seasonMapper.toDomain(entity.getSeason()),
                playerMapper.toDomain(entity.getPlayer()),
                entity.getTotalReg(),
                entity.getTotalPro()
        );
    }
}
