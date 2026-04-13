package com.antonk404.poker_rating.infrastructure.persistence.repository;

import com.antonk404.poker_rating.infrastructure.persistence.entity.TournamentParticipantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TournamentParticipantJpaRepository extends JpaRepository<TournamentParticipantJpaEntity, UUID> {

    List<TournamentParticipantJpaEntity> findByTournament_Id(UUID tournamentId);

    List<TournamentParticipantJpaEntity> findByPlayer_IdAndTournament_Season_Id(UUID playerId, UUID seasonId);
}
