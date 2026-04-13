package com.antonk404.poker_rating.infrastructure.persistence.repository;

import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonRatingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SeasonRatingJpaRepository extends JpaRepository<SeasonRatingJpaEntity, UUID> {

    Optional<SeasonRatingJpaEntity> findBySeason_IdAndPlayer_Id(UUID seasonId, UUID playerId);
}
