package com.antonk404.poker_rating.repository;

import com.antonk404.poker_rating.entity.Knockout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KnockoutRepository extends JpaRepository<Knockout, UUID> {
}
