package com.antonk404.poker_rating.entity.dto;

import com.antonk404.poker_rating.entity.Tournament;
import com.antonk404.poker_rating.entity.enums.TournamentType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TournamentDto {
    private UUID id;
    private SeasonDto seasonDto;
    private String name;
    private TournamentType type;
    private Integer playerCount;
    private Integer bountyBase;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public TournamentDto(SeasonDto seasonDto, String name, TournamentType type, Integer playerCount, Integer bountyBase, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.seasonDto = seasonDto;
        this.name = name;
        this.type = type;
        this.playerCount = playerCount;
        this.bountyBase = bountyBase;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public TournamentDto(Tournament tournamentEntity) {
        this.id = tournamentEntity.getId();
        this.seasonDto = tournamentEntity.getSeason().toDto();
        this.name = tournamentEntity.getName();
        this.type = tournamentEntity.getType();
        this.playerCount = tournamentEntity.getPlayerCount();
        this.bountyBase = tournamentEntity.getBountyBase();
        this.startedAt = tournamentEntity.getStartedAt();
        this.endedAt = tournamentEntity.getEndedAt();
    }

    public Tournament toEntity() {
        return new Tournament(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SeasonDto getSeasonDto() {
        return seasonDto;
    }

    public void setSeasonDto(SeasonDto seasonDto) {
        this.seasonDto = seasonDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TournamentType getType() {
        return type;
    }

    public void setType(TournamentType type) {
        this.type = type;
    }

    public Integer getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Integer playerCount) {
        this.playerCount = playerCount;
    }

    public Integer getBountyBase() {
        return bountyBase;
    }

    public void setBountyBase(Integer bountyBase) {
        this.bountyBase = bountyBase;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TournamentDto that = (TournamentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(seasonDto, that.seasonDto) && Objects.equals(name, that.name) && type == that.type && Objects.equals(playerCount, that.playerCount) && Objects.equals(bountyBase, that.bountyBase) && Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonDto, name, type, playerCount, bountyBase, startedAt, endedAt);
    }
}
