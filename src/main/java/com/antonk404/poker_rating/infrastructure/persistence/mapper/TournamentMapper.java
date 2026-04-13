package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.TournamentJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapper {

    private final SeasonMapper seasonMapper;

    public TournamentMapper(SeasonMapper seasonMapper) {
        this.seasonMapper = seasonMapper;
    }

    public Tournament toDomain(TournamentJpaEntity entity) {
        return new Tournament(
                entity.getId(),
                seasonMapper.toDomain(entity.getSeason()),
                entity.getName(),
                entity.getType(),
                entity.getPlayerCount(),
                entity.getBountyBase(),
                entity.getStartedAt(),
                entity.getEndedAt()
        );
    }

    public TournamentJpaEntity toJpaEntity(Tournament domain) {
        SeasonJpaEntity seasonRef = new SeasonJpaEntity();
        seasonRef.setId(domain.getSeason().getId());

        TournamentJpaEntity entity = new TournamentJpaEntity();
        entity.setId(domain.getId());
        entity.setSeason(seasonRef);
        entity.setName(domain.getName());
        entity.setType(domain.getType());
        entity.setPlayerCount(domain.getPlayerCount());
        entity.setBountyBase(domain.getBountyBase());
        entity.setStartedAt(domain.getStartedAt());
        entity.setEndedAt(domain.getEndedAt());
        return entity;
    }
}
