package com.antonk404.poker_rating.repository;

import com.antonk404.poker_rating.entity.TournamentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, UUID> {
}
