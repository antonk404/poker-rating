package com.antonk404.poker_rating.domain.model;

import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Season {

    private final UUID id;
    private Integer year;
    private Integer month;
    private Integer topResultsCount;
    private SeasonStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Season(UUID id, Integer year, Integer month, Integer topResultsCount,
                  SeasonStatus status, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.topResultsCount = topResultsCount;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public UUID getId() { return id; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getTopResultsCount() { return topResultsCount; }
    public void setTopResultsCount(Integer topResultsCount) { this.topResultsCount = topResultsCount; }

    public SeasonStatus getStatus() { return status; }
    public void setStatus(SeasonStatus status) { this.status = status; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id, season.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
