package com.antonk404.poker_rating.domain.port.out;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.TournamentParticipant;

import java.util.List;

public interface TournamentParticipantRepositoryPort {
    List<TournamentParticipant> findByTournament(Tournament tournament);
    List<TournamentParticipant> findByPlayerAndSeason(Player player, Season season);
    TournamentParticipant save(TournamentParticipant participant);
    void saveAll(List<TournamentParticipant> participants);
}
