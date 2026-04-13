package com.antonk404.poker_rating.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TournamentParticipant {

    private final UUID id;
    private Tournament tournament;
    private Player player;
    private Integer position;
    private LocalDateTime eliminatedAt;
    private int regPoints;
    private int proPoints;
    private int bountyPoints;

    public TournamentParticipant(UUID id, Tournament tournament, Player player,
                                 Integer position, LocalDateTime eliminatedAt,
                                 int regPoints, int proPoints, int bountyPoints) {
        this.id = id;
        this.tournament = tournament;
        this.player = player;
        this.position = position;
        this.eliminatedAt = eliminatedAt;
        this.regPoints = regPoints;
        this.proPoints = proPoints;
        this.bountyPoints = bountyPoints;
    }

    public UUID getId() { return id; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipant that = (TournamentParticipant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
