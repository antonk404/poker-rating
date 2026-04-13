package com.antonk404.poker_rating.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Player {

    private final UUID id;
    private String username;
    private int rating;
    private int proRating;

    public Player(UUID id, String username, int rating, int proRating) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.proRating = proRating;
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
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
