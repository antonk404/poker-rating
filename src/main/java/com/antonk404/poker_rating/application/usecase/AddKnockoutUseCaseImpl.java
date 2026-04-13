package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Knockout;
import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.port.in.AddKnockoutUseCase;
import com.antonk404.poker_rating.domain.port.out.KnockoutRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddKnockoutUseCaseImpl implements AddKnockoutUseCase {

    private final TournamentRepositoryPort tournamentPort;
    private final PlayerRepositoryPort playerPort;
    private final KnockoutRepositoryPort knockoutPort;

    public AddKnockoutUseCaseImpl(
            TournamentRepositoryPort tournamentPort,
            PlayerRepositoryPort playerPort,
            KnockoutRepositoryPort knockoutPort) {
        this.tournamentPort = tournamentPort;
        this.playerPort = playerPort;
        this.knockoutPort = knockoutPort;
    }

    @Override
    @Transactional
    public Knockout addKnockout(UUID tournamentId, UUID killerId, UUID victimId, int bountyPoints) {
        Tournament tournament = tournamentPort.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + tournamentId));
        Player killer = playerPort.findById(killerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + killerId));
        Player victim = playerPort.findById(victimId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + victimId));
        return knockoutPort.save(new Knockout(null, tournament, killer, victim, bountyPoints));
    }
}
