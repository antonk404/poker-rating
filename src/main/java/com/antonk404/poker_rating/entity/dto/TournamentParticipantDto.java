package com.antonk404.poker_rating.entity.dto;

import com.antonk404.poker_rating.entity.TournamentParticipant;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TournamentParticipantDto {
    private UUID id;
    private TournamentDto tournamentDto;
    private PlayerDto playerDto;
    private Integer position;
    private LocalDateTime eliminatedAt;
    private Integer regPoints;
    private Integer proPoints;
    private Integer bountyPoints;

    public TournamentParticipantDto(TournamentDto tournamentDto, PlayerDto playerDto, Integer position, LocalDateTime eliminatedAt, Integer regPoints, Integer proPoints, Integer bountyPoints) {
        this.tournamentDto = tournamentDto;
        this.playerDto = playerDto;
        this.position = position;
        this.eliminatedAt = eliminatedAt;
        this.regPoints = regPoints;
        this.proPoints = proPoints;
        this.bountyPoints = bountyPoints;
    }

    public TournamentParticipantDto(TournamentParticipant tournamentParticipantEntity) {
        this.id = tournamentParticipantEntity.getId();
        this.tournamentDto = tournamentParticipantEntity.getTournament().toDto();
        this.playerDto = tournamentParticipantEntity.getPlayer().toDto();
        this.position = tournamentParticipantEntity.getPosition();
        this.eliminatedAt = tournamentParticipantEntity.getEliminatedAt();
        this.regPoints = tournamentParticipantEntity.getRegPoints();
        this.proPoints = tournamentParticipantEntity.getProPoints();
        this.bountyPoints = tournamentParticipantEntity.getBountyPoints();
    }

    public TournamentParticipant toEntity() {
        return new TournamentParticipant(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TournamentDto getTournamentDto() {
        return tournamentDto;
    }

    public void setTournamentDto(TournamentDto tournamentDto) {
        this.tournamentDto = tournamentDto;
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public LocalDateTime getEliminatedAt() {
        return eliminatedAt;
    }

    public void setEliminatedAt(LocalDateTime eliminatedAt) {
        this.eliminatedAt = eliminatedAt;
    }

    public Integer getRegPoints() {
        return regPoints;
    }

    public void setRegPoints(Integer regPoints) {
        this.regPoints = regPoints;
    }

    public Integer getProPoints() {
        return proPoints;
    }

    public void setProPoints(Integer proPoints) {
        this.proPoints = proPoints;
    }

    public Integer getBountyPoints() {
        return bountyPoints;
    }

    public void setBountyPoints(Integer bountyPoints) {
        this.bountyPoints = bountyPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantDto that = (TournamentParticipantDto) o;
        return Objects.equals(id, that.id) && Objects.equals(tournamentDto, that.tournamentDto) && Objects.equals(playerDto, that.playerDto) && Objects.equals(position, that.position) && Objects.equals(eliminatedAt, that.eliminatedAt) && Objects.equals(regPoints, that.regPoints) && Objects.equals(proPoints, that.proPoints) && Objects.equals(bountyPoints, that.bountyPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournamentDto, playerDto, position, eliminatedAt, regPoints, proPoints, bountyPoints);
    }
}
