package com.antonk404.poker_rating.infrastructure.persistence.adapter;

import com.antonk404.poker_rating.domain.model.Player;
import com.antonk404.poker_rating.domain.model.Season;
import com.antonk404.poker_rating.domain.model.SeasonRating;
import com.antonk404.poker_rating.domain.port.out.SeasonRatingRepositoryPort;
import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonRatingJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.PlayerJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.entity.SeasonJpaEntity;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.PlayerMapper;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.SeasonMapper;
import com.antonk404.poker_rating.infrastructure.persistence.mapper.SeasonRatingMapper;
import com.antonk404.poker_rating.infrastructure.persistence.repository.SeasonRatingJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SeasonRatingPersistenceAdapter implements SeasonRatingRepositoryPort {

    private final SeasonRatingJpaRepository repository;
    private final SeasonRatingMapper mapper;
    private final SeasonMapper seasonMapper;
    private final PlayerMapper playerMapper;

    public SeasonRatingPersistenceAdapter(
            SeasonRatingJpaRepository repository,
            SeasonRatingMapper mapper,
            SeasonMapper seasonMapper,
            PlayerMapper playerMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.seasonMapper = seasonMapper;
        this.playerMapper = playerMapper;
    }

    @Override
    public Optional<SeasonRating> findBySeasonAndPlayer(Season season, Player player) {
        return repository.findBySeason_IdAndPlayer_Id(season.getId(), player.getId())
                .map(mapper::toDomain);
    }

    @Override
    public SeasonRating save(SeasonRating seasonRating) {
        SeasonRatingJpaEntity entity = seasonRating.getId() != null
                ? repository.findById(seasonRating.getId()).orElseGet(SeasonRatingJpaEntity::new)
                : new SeasonRatingJpaEntity();

        SeasonJpaEntity seasonRef = new SeasonJpaEntity();
        seasonRef.setId(seasonRating.getSeason().getId());

        PlayerJpaEntity playerRef = new PlayerJpaEntity();
        playerRef.setId(seasonRating.getPlayer().getId());

        entity.setSeason(seasonRef);
        entity.setPlayer(playerRef);
        entity.setTotalReg(seasonRating.getTotalReg());
        entity.setTotalPro(seasonRating.getTotalPro());
        return mapper.toDomain(repository.save(entity));
    }
}
