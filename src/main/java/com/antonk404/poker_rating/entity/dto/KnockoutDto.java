package com.antonk404.poker_rating.entity.dto;

import com.antonk404.poker_rating.entity.Knockout;

import java.util.Objects;
import java.util.UUID;

public class KnockoutDto {
    private UUID id;
    private TournamentDto tournamentDto;
    private PlayerDto killerDto;
    private PlayerDto victimDto;
    private int bountyPoints;

    public KnockoutDto(TournamentDto tournamentDto, PlayerDto killerDto, PlayerDto victimDto, int bountyPoints) {
        this.tournamentDto = tournamentDto;
        this.killerDto = killerDto;
        this.victimDto = victimDto;
        this.bountyPoints = bountyPoints;
    }

    public KnockoutDto(Knockout knockoutEntity) {
        this.id = knockoutEntity.getId();
        this.tournamentDto = knockoutEntity.getTournament().toDto();
        this.killerDto = knockoutEntity.getKiller().toDto();
        this.victimDto = knockoutEntity.getVictim().toDto();
        this.bountyPoints = knockoutEntity.getBountyPoints();
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

    public PlayerDto getKillerDto() {
        return killerDto;
    }

    public void setKillerDto(PlayerDto killerDto) {
        this.killerDto = killerDto;
    }

    public PlayerDto getVictimDto() {
        return victimDto;
    }

    public void setVictimDto(PlayerDto victimDto) {
        this.victimDto = victimDto;
    }

    public int getBountyPoints() {
        return bountyPoints;
    }

    public void setBountyPoints(int bountyPoints) {
        this.bountyPoints = bountyPoints;
    }

    public Knockout toEntity() {
        return new Knockout(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KnockoutDto that = (KnockoutDto) o;
        return bountyPoints == that.bountyPoints && Objects.equals(id, that.id) && Objects.equals(tournamentDto, that.tournamentDto) && Objects.equals(killerDto, that.killerDto) && Objects.equals(victimDto, that.victimDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournamentDto, killerDto, victimDto, bountyPoints);
    }
}
