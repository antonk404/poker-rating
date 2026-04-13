package com.antonk404.poker_rating.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tournament_participants")
public class TournamentParticipantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentJpaEntity tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerJpaEntity player;

    @Column
    private Integer position;

    @Column(name = "eliminated_at")
    private LocalDateTime eliminatedAt;

    @Column(name = "reg_points", nullable = false)
    private int regPoints;

    @Column(name = "pro_points", nullable = false)
    private int proPoints;

    @Column(name = "bounty_points", nullable = false)
    private int bountyPoints;

    public TournamentParticipantJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public TournamentJpaEntity getTournament() { return tournament; }
    public void setTournament(TournamentJpaEntity tournament) { this.tournament = tournament; }

    public PlayerJpaEntity getPlayer() { return player; }
    public void setPlayer(PlayerJpaEntity player) { this.player = player; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public LocalDateTime getEliminatedAt() { return eliminatedAt; }
    public void setEliminatedAt(LocalDateTime eliminatedAt) { this.eliminatedAt = eliminatedAt; }

    public int getRegPoints() { return regPoints; }
    public void setRegPoints(int regPoints) { this.regPoints = regPoints; }

    public int getProPoints() { return proPoints; }
    public void setProPoints(int proPoints) { this.proPoints = proPoints; }

    public int getBountyPoints() { return bountyPoints; }
    public void setBountyPoints(int bountyPoints) { this.bountyPoints = bountyPoints; }
}
