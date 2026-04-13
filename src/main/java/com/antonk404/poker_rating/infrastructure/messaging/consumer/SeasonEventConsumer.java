package com.antonk404.poker_rating.infrastructure.messaging.consumer;

import com.antonk404.poker_rating.domain.port.in.CreateSeasonUseCase;
import com.antonk404.poker_rating.infrastructure.messaging.event.SeasonCreateEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SeasonEventConsumer {

    private final CreateSeasonUseCase createSeasonUseCase;

    public SeasonEventConsumer(CreateSeasonUseCase createSeasonUseCase) {
        this.createSeasonUseCase = createSeasonUseCase;
    }

    @KafkaListener(topics = "poker-rating.season.create", groupId = "${spring.kafka.consumer.group-id}")
    public void onSeasonCreate(SeasonCreateEvent event) {
        createSeasonUseCase.createSeason(
                event.year(),
                event.month(),
                event.topResultsCount(),
                event.status(),
                event.startedAt(),
                event.endedAt()
        );
    }
}
