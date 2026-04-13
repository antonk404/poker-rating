package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Tournament;
import com.antonk404.poker_rating.domain.model.TournamentParticipant;
import com.antonk404.poker_rating.domain.port.in.RegisterParticipantUseCase;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentParticipantRepositoryPort;
import com.antonk404.poker_rating.domain.port.out.TournamentRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegisterParticipantUseCaseImpl implements RegisterParticipantUseCase {

    private final TournamentRepositoryPort tournamentPort;
    private final PlayerRepositoryPort playerPort;
    private final TournamentParticipantRepositoryPort participantPort;

    public RegisterParticipantUseCaseImpl(
            TournamentRepositoryPort tournamentPort,
            PlayerRepositoryPort playerPort,
            TournamentParticipantRepositoryPort participantPort) {
        this.tournamentPort = tournamentPort;
        this.playerPort = playerPort;
        this.participantPort = participantPort;
    }

    @Override
    @Transactional
    public TournamentParticipant registerParticipant(UUID tournamentId, UUID playerId) {
        Tournament tournament = tournamentPort.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + tournamentId));
        Player player = playerPort.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + playerId));
        return participantPort.save(new TournamentParticipant(null, tournament, player, null, null, 0, 0, 0));
    }
}
