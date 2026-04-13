package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.Knockout;
import com.antonk404.poker_rating.infrastructure.persistence.entity.KnockoutJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.PlayerJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.TournamentJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class KnockoutMapper {

    private final TournamentMapper tournamentMapper;
    private final PlayerMapper playerMapper;

    public KnockoutMapper(TournamentMapper tournamentMapper, PlayerMapper playerMapper) {
        this.tournamentMapper = tournamentMapper;
        this.playerMapper = playerMapper;
    }

    public Knockout toDomain(KnockoutJpaEntity entity) {
        return new Knockout(
                entity.getId(),
                tournamentMapper.toDomain(entity.getTournament()),
                playerMapper.toDomain(entity.getKiller()),
                playerMapper.toDomain(entity.getVictim()),
                entity.getBountyPoints()
        );
    }

    public KnockoutJpaEntity toJpaEntity(Knockout domain) {
        TournamentJpaEntity tournamentRef = new TournamentJpaEntity();
        tournamentRef.setId(domain.getTournament().getId());

        PlayerJpaEntity killerRef = new PlayerJpaEntity();
        killerRef.setId(domain.getKiller().getId());

        PlayerJpaEntity victimRef = new PlayerJpaEntity();
        victimRef.setId(domain.getVictim().getId());

        KnockoutJpaEntity entity = new KnockoutJpaEntity();
        entity.setId(domain.getId());
        entity.setTournament(tournamentRef);
        entity.setKiller(killerRef);
        entity.setVictim(victimRef);
        entity.setBountyPoints(domain.getBountyPoints());
        return entity;
    }
}
