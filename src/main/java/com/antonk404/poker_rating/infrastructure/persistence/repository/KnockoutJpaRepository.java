package com.antonk404.poker_rating.infrastructure.persistence.repository;

import com.antonk404.poker_rating.infrastructure.persistence.entity.KnockoutJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KnockoutJpaRepository extends JpaRepository<KnockoutJpaEntity, UUID> {

    List<KnockoutJpaEntity> findByTournament_Id(UUID tournamentId);
}
