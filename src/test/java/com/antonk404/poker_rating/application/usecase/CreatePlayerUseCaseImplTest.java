package com.antonk404.poker_rating.application.usecase;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.port.out.PlayerRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlayerUseCaseImplTest {

    @Mock private PlayerRepositoryPort playerPort;
    @InjectMocks private CreatePlayerUseCaseImpl useCase;

    @Test
    void createPlayer_savesPlayerWithZeroRatings() {
        when(playerPort.save(any())).thenAnswer(inv -> {
            Player p = inv.getArgument(0);
            return new Player(UUID.randomUUID(), p.getUsername(), p.getRating(), p.getProRating());
        });

        useCase.createPlayer("anton");

        ArgumentCaptor<Player> captor = ArgumentCaptor.forClass(Player.class);
        verify(playerPort).save(captor.capture());

        Player saved = captor.getValue();
        assertThat(saved.getUsername()).isEqualTo("anton");
        assertThat(saved.getRating()).isZero();
        assertThat(saved.getProRating()).isZero();
    }
}
