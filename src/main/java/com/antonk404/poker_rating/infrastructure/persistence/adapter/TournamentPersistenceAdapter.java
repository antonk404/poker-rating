package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.port.out.TournamentRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.TournamentMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.TournamentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TournamentPersistenceAdapter implements TournamentRepositoryPort {

    private final TournamentJpaRepository repository;
    private final TournamentMapper mapper;

    public TournamentPersistenceAdapter(TournamentJpaRepository repository, TournamentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Tournament save(Tournament tournament) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(tournament)));
    }
}
