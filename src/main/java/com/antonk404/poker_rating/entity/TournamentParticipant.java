package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.dto.TournamentParticipantDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tournament_participants")
public class TournamentParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

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

    public TournamentParticipant() {}

    public TournamentParticipant(Tournament tournament, Player player, Integer position, LocalDateTime eliminatedAt, int regPoints, int proPoints, int bountyPoints) {
        this.tournament = tournament;
        this.player = player;
        this.position = position;
        this.eliminatedAt = eliminatedAt;
        this.regPoints = regPoints;
        this.proPoints = proPoints;
        this.bountyPoints = bountyPoints;
    }

    public TournamentParticipant(TournamentParticipantDto tournamentParticipantDto) {
        this.id = tournamentParticipantDto.getId();
        this.tournament = tournamentParticipantDto.getTournamentDto().toEntity();
        this.player = tournamentParticipantDto.getPlayerDto().toEntity();
        this.position = tournamentParticipantDto.getPosition();
        this.eliminatedAt = tournamentParticipantDto.getEliminatedAt();
        this.regPoints = tournamentParticipantDto.getRegPoints();
        this.proPoints = tournamentParticipantDto.getProPoints();
        this.bountyPoints = tournamentParticipantDto.getBountyPoints();
    }

    public TournamentParticipantDto toDto() {
        return new TournamentParticipantDto(this);
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
        return regPoints == that.regPoints && proPoints == that.proPoints && bountyPoints == that.bountyPoints && Objects.equals(id, that.id) && Objects.equals(tournament, that.tournament) && Objects.equals(player, that.player) && Objects.equals(position, that.position) && Objects.equals(eliminatedAt, that.eliminatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournament, player, position, eliminatedAt, regPoints, proPoints, bountyPoints);
    }
}
