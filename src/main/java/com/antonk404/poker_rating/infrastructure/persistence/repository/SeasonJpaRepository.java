package com.antonk404.poker_rating.infrastructure.persistence.repository;

import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonJpaRepository extends JpaRepository<SeasonJpaEntity, UUID> {
}
