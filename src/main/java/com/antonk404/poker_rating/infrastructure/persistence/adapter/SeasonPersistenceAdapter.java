package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.port.out.SeasonRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.SeasonMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.SeasonJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SeasonPersistenceAdapter implements SeasonRepositoryPort {

    private final SeasonJpaRepository repository;
    private final SeasonMapper mapper;

    public SeasonPersistenceAdapter(SeasonJpaRepository repository, SeasonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Season> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Season save(Season season) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(season)));
    }
}
