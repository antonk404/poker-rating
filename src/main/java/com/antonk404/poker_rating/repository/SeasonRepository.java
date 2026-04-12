package com.antonk404.poker_rating.repository;

import com.antonk404.poker_rating.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
}
