package com.antonk404.poker_rating.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Knockout {

    private final UUID id;
    private Tournament tournament;
    private Player killer;
    private Player victim;
    private int bountyPoints;

    public Knockout(UUID id, Tournament tournament, Player killer, Player victim, int bountyPoints) {
        this.id = id;
        this.tournament = tournament;
        this.killer = killer;
        this.victim = victim;
        this.bountyPoints = bountyPoints;
    }

    public UUID getId() { return id; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public Player getKiller() { return killer; }
    public void setKiller(Player killer) { this.killer = killer; }

    public Player getVictim() { return victim; }
    public void setVictim(Player victim) { this.victim = victim; }

    public int getBountyPoints() { return bountyPoints; }
    public void setBountyPoints(int bountyPoints) { this.bountyPoints = bountyPoints; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Knockout that = (Knockout) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
