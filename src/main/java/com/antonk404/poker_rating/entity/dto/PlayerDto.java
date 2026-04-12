package com.antonk404.poker_rating.entity.dto;

import com.antonk404.poker_rating.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class PlayerDto {
    private UUID id;
    private String username;
    private int rating;
    private int proRating;

    public PlayerDto(String username, int rating, int proRating) {
        this.username = username;
        this.rating = rating;
        this.proRating = proRating;
    }

    public PlayerDto(Player playerEntity) {
        this.id = playerEntity.getId();
        this.username = playerEntity.getUsername();
        this.rating = playerEntity.getRating();
        this.proRating = playerEntity.getProRating();
    }

    public Player toEntity() {
        return new Player(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProRating() {
        return proRating;
    }

    public void setProRating(int proRating) {
        this.proRating = proRating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return rating == playerDto.rating && proRating == playerDto.proRating && Objects.equals(id, playerDto.id) && Objects.equals(username, playerDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, rating, proRating);
    }
}
