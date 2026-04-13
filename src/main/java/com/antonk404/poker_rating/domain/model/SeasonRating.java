package com.antonk404.poker_rating.domain.model;

import java.util.Objects;
import java.util.UUID;

public class SeasonRating {

    private final UUID id;
    private Season season;
    private Player player;
    private Integer totalReg;
    private Integer totalPro;

    public SeasonRating(UUID id, Season season, Player player, Integer totalReg, Integer totalPro) {
        this.id = id;
        this.season = season;
        this.player = player;
        this.totalReg = totalReg;
        this.totalPro = totalPro;
    }

    public UUID getId() { return id; }

    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Integer getTotalReg() { return totalReg; }
    public void setTotalReg(Integer totalReg) { this.totalReg = totalReg; }

    public Integer getTotalPro() { return totalPro; }
    public void setTotalPro(Integer totalPro) { this.totalPro = totalPro; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeasonRating that = (SeasonRating) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
