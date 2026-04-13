package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Knockout;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.port.out.KnockoutRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.KnockoutMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.KnockoutJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnockoutPersistenceAdapter implements KnockoutRepositoryPort {

    private final KnockoutJpaRepository repository;
    private final KnockoutMapper mapper;

    public KnockoutPersistenceAdapter(KnockoutJpaRepository repository, KnockoutMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Knockout> findByTournament(Tournament tournament) {
        return repository.findByTournament_Id(tournament.getId())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public Knockout save(Knockout knockout) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(knockout)));
    }
}
