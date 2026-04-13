package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.TournamentParticipant;
import com.antonk404.poker_rating.domain.port.out.TournamentParticipantRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.TournamentParticipantMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.TournamentParticipantJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentParticipantPersistenceAdapter implements TournamentParticipantRepositoryPort {

    private final TournamentParticipantJpaRepository repository;
    private final TournamentParticipantMapper mapper;

    public TournamentParticipantPersistenceAdapter(
            TournamentParticipantJpaRepository repository,
            TournamentParticipantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<TournamentParticipant> findByTournament(Tournament tournament) {
        return repository.findByTournament_Id(tournament.getId())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<TournamentParticipant> findByPlayerAndSeason(Player player, Season season) {
        return repository.findByPlayer_IdAndTournament_Season_Id(player.getId(), season.getId())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public TournamentParticipant save(TournamentParticipant participant) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(participant)));
    }

    @Override
    public void saveAll(List<TournamentParticipant> participants) {
        for (TournamentParticipant p : participants) {
            repository.findById(p.getId()).ifPresent(entity -> {
                entity.setRegPoints(p.getRegPoints());
                entity.setProPoints(p.getProPoints());
                entity.setBountyPoints(p.getBountyPoints());
            });
        }
    }
}
