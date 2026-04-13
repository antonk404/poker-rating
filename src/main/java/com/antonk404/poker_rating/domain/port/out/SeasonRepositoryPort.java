package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Season;

import java.util.Optional;
import java.util.UUID;

public interface SeasonRepositoryPort {
    Optional<Season> findById(UUID id);
    Season save(Season season);
}
