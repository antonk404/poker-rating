package com.antonk404.poker_rating.entity.dto;


import com.antonk404.poker_rating.entity.SeasonRating;

import java.util.Objects;
import java.util.UUID;

public class SeasonRatingDto {
    private UUID id;
    private SeasonDto seasonDto;
    private PlayerDto playerDto;
    private Integer totalReg;
    private Integer totalPro;

    public SeasonRatingDto(SeasonDto seasonDto, PlayerDto playerDto, Integer totalReg, Integer totalPro) {
        this.seasonDto = seasonDto;
        this.playerDto = playerDto;
        this.totalReg = totalReg;
        this.totalPro = totalPro;
    }

    public SeasonRatingDto(SeasonRating seasonRatingEntity) {
        this.id = seasonRatingEntity.getId();
        this.seasonDto = seasonRatingEntity.getSeason().toDto();
        this.playerDto = seasonRatingEntity.getPlayer().toDto();
        this.totalReg = seasonRatingEntity.getTotalReg();
        this.totalPro = seasonRatingEntity.getTotalPro();
    }

    public SeasonRating toEntity() {
        return new SeasonRating(this);
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

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }

    public Integer getTotalReg() {
        return totalReg;
    }

    public void setTotalReg(Integer totalReg) {
        this.totalReg = totalReg;
    }

    public Integer getTotalPro() {
        return totalPro;
    }

    public void setTotalPro(Integer totalPro) {
        this.totalPro = totalPro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeasonRatingDto that = (SeasonRatingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(seasonDto, that.seasonDto) && Objects.equals(playerDto, that.playerDto) && Objects.equals(totalReg, that.totalReg) && Objects.equals(totalPro, that.totalPro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonDto, playerDto, totalReg, totalPro);
    }
}
