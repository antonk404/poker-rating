package com.antonk404.poker_rating.infrastructure.persistence.entity;

import com.antonk404.poker_rating.domain.model.enums.TournamentType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class TournamentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private SeasonJpaEntity season;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TournamentType type;

    @Column(name = "player_count", nullable = false)
    private Integer playerCount;

    @Column(name = "bounty_base")
    private Integer bountyBase;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public TournamentJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public SeasonJpaEntity getSeason() { return season; }
    public void setSeason(SeasonJpaEntity season) { this.season = season; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TournamentType getType() { return type; }
    public void setType(TournamentType type) { this.type = type; }

    public Integer getPlayerCount() { return playerCount; }
    public void setPlayerCount(Integer playerCount) { this.playerCount = playerCount; }

    public Integer getBountyBase() { return bountyBase; }
    public void setBountyBase(Integer bountyBase) { this.bountyBase = bountyBase; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }
}
