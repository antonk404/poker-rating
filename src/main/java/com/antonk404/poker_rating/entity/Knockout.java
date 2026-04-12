package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.dto.KnockoutDto;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "knockouts")
public class Knockout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "killer_id", nullable = false)
    private Player killer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "victim_id", nullable = false)
    private Player victim;

    @Column(name = "bounty_points", nullable = false)
    private int bountyPoints;

    public Knockout() {

    }

    public Knockout(Tournament tournament, Player killer, Player victim, int bountyPoints) {
        this.tournament = tournament;
        this.killer = killer;
        this.victim = victim;
        this.bountyPoints = bountyPoints;
    }

    public Knockout(KnockoutDto knockoutDto) {
        this.id = knockoutDto.getId();
        this.tournament = knockoutDto.getTournamentDto().toEntity();
        this.killer = knockoutDto.getKillerDto().toEntity();
        this.victim = knockoutDto.getVictimDto().toEntity();
        this.bountyPoints = knockoutDto.getBountyPoints();
    }

    public KnockoutDto toDto() {
        return new KnockoutDto(this);
    }

    public UUID getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getKiller() {
        return killer;
    }

    public void setKiller(Player killer) {
        this.killer = killer;
    }

    public Player getVictim() {
        return victim;
    }

    public void setVictim(Player victim) {
        this.victim = victim;
    }

    public int getBountyPoints() {
        return bountyPoints;
    }

    public void setBountyPoints(int bountyPoints) {
        this.bountyPoints = bountyPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Knockout knockout = (Knockout) o;
        return bountyPoints == knockout.bountyPoints && Objects.equals(id, knockout.id) && Objects.equals(tournament, knockout.tournament) && Objects.equals(killer, knockout.killer) && Objects.equals(victim, knockout.victim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournament, killer, victim, bountyPoints);
    }
}
