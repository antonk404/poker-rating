package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.dto.PlayerDto;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int rating;

    @Column(name = "pro_rating", nullable = false)
    private int proRating;

    public Player() {

    }

    public Player(String username, int rating, int proRating) {
        this.username = username;
        this.rating = rating;
        this.proRating = proRating;
    }

    public Player(PlayerDto playerDto) {
        this.id = playerDto.getId();
        this.username = playerDto.getUsername();
        this.rating = playerDto.getRating();
        this.proRating = playerDto.getProRating();
    }

    public PlayerDto toDto() {
        return new PlayerDto(this);
    }

    public UUID getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public int getProRating() { return proRating; }
    public void setProRating(int proRating) { this.proRating = proRating; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return rating == player.rating && proRating == player.proRating && Objects.equals(id, player.id) && Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, rating, proRating);
    }
}
