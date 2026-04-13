package com.antonk404.poker_rating.domain.port.in;

import com.antonk404.poker_rating.domain.model.Knockout;

import java.util.UUID;

public interface AddKnockoutUseCase {
    Knockout addKnockout(UUID tournamentId, UUID killerId, UUID victimId, int bountyPoints);
}
