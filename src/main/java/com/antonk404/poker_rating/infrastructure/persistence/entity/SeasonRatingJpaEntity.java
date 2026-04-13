package com.antonk404.poker_rating.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "season_ratings")
public class SeasonRatingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private SeasonJpaEntity season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerJpaEntity player;

    @Column(name = "total_reg", nullable = false)
    private Integer totalReg;

    @Column(name = "total_pro", nullable = false)
    private Integer totalPro;

    public SeasonRatingJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public SeasonJpaEntity getSeason() { return season; }
    public void setSeason(SeasonJpaEntity season) { this.season = season; }

    public PlayerJpaEntity getPlayer() { return player; }
    public void setPlayer(PlayerJpaEntity player) { this.player = player; }

    public Integer getTotalReg() { return totalReg; }
    public void setTotalReg(Integer totalReg) { this.totalReg = totalReg; }

    public Integer getTotalPro() { return totalPro; }
    public void setTotalPro(Integer totalPro) { this.totalPro = totalPro; }
}
