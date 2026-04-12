package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.dto.TournamentDto;
import com.antonk404.poker_rating.entity.enums.TournamentType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

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

    public Tournament() {}

    public Tournament(Season season, String name, TournamentType type, Integer playerCount, Integer bountyBase, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.season = season;
        this.name = name;
        this.type = type;
        this.playerCount = playerCount;
        this.bountyBase = bountyBase;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Tournament(TournamentDto tournamentDto) {
        this.id = tournamentDto.getId();
        this.season = tournamentDto.getSeasonDto().toEntity();
        this.name = tournamentDto.getName();
        this.type = tournamentDto.getType();
        this.playerCount = tournamentDto.getPlayerCount();
        this.bountyBase = tournamentDto.getBountyBase();
        this.startedAt = tournamentDto.getStartedAt();
        this.endedAt = tournamentDto.getEndedAt();
    }

    public TournamentDto toDto() {
        return new TournamentDto(this);
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
        return Objects.equals(id, that.id) && Objects.equals(season, that.season) && Objects.equals(name, that.name) && type == that.type && Objects.equals(playerCount, that.playerCount) && Objects.equals(bountyBase, that.bountyBase) && Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, season, name, type, playerCount, bountyBase, startedAt, endedAt);
    }
}
