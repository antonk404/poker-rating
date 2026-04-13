package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.SeasonRating;

import java.util.Optional;

public interface SeasonRatingRepositoryPort {
    Optional<SeasonRating> findBySeasonAndPlayer(Season season, Player player);
    SeasonRating save(SeasonRating seasonRating);
}
