package com.antonk404.poker_rating.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "knockouts")
public class KnockoutJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentJpaEntity tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "killer_id", nullable = false)
    private PlayerJpaEntity killer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "victim_id", nullable = false)
    private PlayerJpaEntity victim;

    @Column(name = "bounty_points", nullable = false)
    private int bountyPoints;

    public KnockoutJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public TournamentJpaEntity getTournament() { return tournament; }
    public void setTournament(TournamentJpaEntity tournament) { this.tournament = tournament; }

    public PlayerJpaEntity getKiller() { return killer; }
    public void setKiller(PlayerJpaEntity killer) { this.killer = killer; }

    public PlayerJpaEntity getVictim() { return victim; }
    public void setVictim(PlayerJpaEntity victim) { this.victim = victim; }

    public int getBountyPoints() { return bountyPoints; }
    public void setBountyPoints(int bountyPoints) { this.bountyPoints = bountyPoints; }
}
