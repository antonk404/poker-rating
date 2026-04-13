package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Knockout;
import com.antonk404.poker_rating.domain.model.Tournament;

import java.util.List;

public interface KnockoutRepositoryPort {
    List<Knockout> findByTournament(Tournament tournament);
    Knockout save(Knockout knockout);
}
