package com.antonk404.poker_rating.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players")
public class PlayerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int rating;

    @Column(name = "pro_rating", nullable = false)
    private int proRating;

    public PlayerJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public int getProRating() { return proRating; }
    public void setProRating(int proRating) { this.proRating = proRating; }
}
