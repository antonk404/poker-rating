package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.dto.SeasonRatingDto;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "season_ratings")
public class SeasonRating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "total_reg", nullable = false)
    private Integer totalReg;

    @Column(name = "total_pro", nullable = false)
    private Integer totalPro;

    public SeasonRating() {

    }

    public SeasonRating(Season season, Player player, Integer totalReg, Integer totalPro) {
        this.season = season;
        this.player = player;
        this.totalReg = totalReg;
        this.totalPro = totalPro;
    }

    public SeasonRating(SeasonRatingDto seasonRatingDto) {
        this.id = seasonRatingDto.getId();
        this.season = seasonRatingDto.getSeasonDto().toEntity();
        this.player = seasonRatingDto.getPlayerDto().toEntity();
        this.totalReg = seasonRatingDto.getTotalReg();
        this.totalPro = seasonRatingDto.getTotalPro();
    }

    public SeasonRatingDto toDto() {
        return new SeasonRatingDto(this);
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
        return Objects.equals(id, that.id) && Objects.equals(season, that.season) && Objects.equals(player, that.player) && Objects.equals(totalReg, that.totalReg) && Objects.equals(totalPro, that.totalPro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, season, player, totalReg, totalPro);
    }
}
