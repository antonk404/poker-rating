package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public Season toDomain(SeasonJpaEntity entity) {
        return new Season(
                entity.getId(),
                entity.getYear(),
                entity.getMonth(),
                entity.getTopResultsCount(),
                entity.getStatus(),
                entity.getStartedAt(),
                entity.getEndedAt()
        );
    }

    public SeasonJpaEntity toJpaEntity(Season domain) {
        SeasonJpaEntity entity = new SeasonJpaEntity();
        entity.setId(domain.getId());
        entity.setYear(domain.getYear());
        entity.setMonth(domain.getMonth());
        entity.setTopResultsCount(domain.getTopResultsCount());
        entity.setStatus(domain.getStatus());
        entity.setStartedAt(domain.getStartedAt());
        entity.setEndedAt(domain.getEndedAt());
        return entity;
    }
}
