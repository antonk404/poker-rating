package com.antonk404.poker_rating.infrastructure.messaging.consumer;

import com.antonk404.poker_rating.domain.port.in.CreatePlayerUseCase;
import com.antonk404.poker_rating.infrastructure.messaging.event.PlayerCreateEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PlayerEventConsumer {

    private final CreatePlayerUseCase createPlayerUseCase;

    public PlayerEventConsumer(CreatePlayerUseCase createPlayerUseCase) {
        this.createPlayerUseCase = createPlayerUseCase;
    }

    @KafkaListener(topics = "poker-rating.player.create", groupId = "${spring.kafka.consumer.group-id}")
    public void onPlayerCreate(PlayerCreateEvent event) {
        createPlayerUseCase.createPlayer(event.username());
    }
}
