package com.antonk404.poker_rating.infrastructure.persistence.mapper;

import com.antonk404.poker_rating.domain.model.TournamentParticipant;
import com.antonk404.poker_rating.infrastructure.persistence.entity.PlayerJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.TournamentJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.TournamentParticipantJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TournamentParticipantMapper {

    private final TournamentMapper tournamentMapper;
    private final PlayerMapper playerMapper;

    public TournamentParticipantMapper(TournamentMapper tournamentMapper, PlayerMapper playerMapper) {
        this.tournamentMapper = tournamentMapper;
        this.playerMapper = playerMapper;
    }

    public TournamentParticipant toDomain(TournamentParticipantJpaEntity entity) {
        return new TournamentParticipant(
                entity.getId(),
                tournamentMapper.toDomain(entity.getTournament()),
                playerMapper.toDomain(entity.getPlayer()),
                entity.getPosition(),
                entity.getEliminatedAt(),
                entity.getRegPoints(),
                entity.getProPoints(),
                entity.getBountyPoints()
        );
    }

    public TournamentParticipantJpaEntity toJpaEntity(TournamentParticipant domain) {
        TournamentJpaEntity tournamentRef = new TournamentJpaEntity();
        tournamentRef.setId(domain.getTournament().getId());

        PlayerJpaEntity playerRef = new PlayerJpaEntity();
        playerRef.setId(domain.getPlayer().getId());

        TournamentParticipantJpaEntity entity = new TournamentParticipantJpaEntity();
        entity.setId(domain.getId());
        entity.setTournament(tournamentRef);
        entity.setPlayer(playerRef);
        entity.setPosition(domain.getPosition());
        entity.setEliminatedAt(domain.getEliminatedAt());
        entity.setRegPoints(domain.getRegPoints());
        entity.setProPoints(domain.getProPoints());
        entity.setBountyPoints(domain.getBountyPoints());
        return entity;
    }
}
