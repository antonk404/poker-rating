package com.antonk404.poker_rating.domain.model;

import com.antonk404.poker_rating.domain.model.enums.TournamentType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Tournament {

    private final UUID id;
    private Season season;
    private String name;
    private TournamentType type;
    private Integer playerCount;
    private Integer bountyBase;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Tournament(UUID id, Season season, String name, TournamentType type,
                      Integer playerCount, Integer bountyBase,
                      LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.season = season;
        this.name = name;
        this.type = type;
        this.playerCount = playerCount;
        this.bountyBase = bountyBase;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public UUID getId() { return id; }

    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
