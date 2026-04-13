package com.antonk404.poker_rating.infrastructure.messaging.consumer;

import com.antonk404.poker_rating.domain.port.in.AddKnockoutUseCase;
import com.antonk404.poker_rating.domain.port.in.CreateTournamentUseCase;
import com.antonk404.poker_rating.domain.port.in.RegisterParticipantUseCase;
import com.antonk404.poker_rating.domain.port.in.ScoreTournamentUseCase;
import com.antonk404.poker_rating.infrastructure.messaging.event.KnockoutAddEvent;
import com.antonk404.poker_rating.infrastructure.messaging.event.ParticipantRegisterEvent;
import com.antonk404.poker_rating.infrastructure.messaging.event.TournamentCreateEvent;
import com.antonk404.poker_rating.infrastructure.messaging.event.TournamentScoreEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TournamentEventConsumer {

    private final CreateTournamentUseCase createTournamentUseCase;
    private final RegisterParticipantUseCase registerParticipantUseCase;
    private final AddKnockoutUseCase addKnockoutUseCase;
    private final ScoreTournamentUseCase scoreTournamentUseCase;

    public TournamentEventConsumer(
            CreateTournamentUseCase createTournamentUseCase,
            RegisterParticipantUseCase registerParticipantUseCase,
            AddKnockoutUseCase addKnockoutUseCase,
            ScoreTournamentUseCase scoreTournamentUseCase) {
        this.createTournamentUseCase = createTournamentUseCase;
        this.registerParticipantUseCase = registerParticipantUseCase;
        this.addKnockoutUseCase = addKnockoutUseCase;
        this.scoreTournamentUseCase = scoreTournamentUseCase;
    }

    @KafkaListener(topics = "poker-rating.tournament.create", groupId = "${spring.kafka.consumer.group-id}")
    public void onTournamentCreate(TournamentCreateEvent event) {
        createTournamentUseCase.createTournament(
                event.seasonId(),
                event.name(),
                event.type(),
                event.playerCount(),
                event.bountyBase(),
                event.startedAt(),
                event.endedAt()
        );
    }

    @KafkaListener(topics = "poker-rating.tournament.register-participant", groupId = "${spring.kafka.consumer.group-id}")
    public void onParticipantRegister(ParticipantRegisterEvent event) {
        registerParticipantUseCase.registerParticipant(event.tournamentId(), event.playerId());
    }

    @KafkaListener(topics = "poker-rating.tournament.add-knockout", groupId = "${spring.kafka.consumer.group-id}")
    public void onKnockoutAdd(KnockoutAddEvent event) {
        addKnockoutUseCase.addKnockout(event.tournamentId(), event.killerId(), event.victimId(), event.bountyPoints());
    }

    @KafkaListener(topics = "poker-rating.tournament.score", groupId = "${spring.kafka.consumer.group-id}")
    public void onTournamentScore(TournamentScoreEvent event) {
        scoreTournamentUseCase.scoreTournament(event.tournamentId());
    }
}
