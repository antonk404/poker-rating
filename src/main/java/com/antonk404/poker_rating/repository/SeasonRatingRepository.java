package com.antonk404.poker_rating.repository;

import com.antonk404.poker_rating.entity.SeasonRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRatingRepository extends JpaRepository<SeasonRating, UUID> {
}
