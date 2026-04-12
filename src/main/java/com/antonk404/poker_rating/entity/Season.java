package com.antonk404.poker_rating.entity;

import com.antonk404.poker_rating.entity.constant.SeasonConstants;
import com.antonk404.poker_rating.entity.dto.SeasonDto;
import com.antonk404.poker_rating.entity.enums.SeasonStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(name = "top_results_count", nullable = false)
    private Integer topResultsCount = SeasonConstants.DEFAULT_TOP_RESULTS_COUNT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonStatus status;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public Season() {

    }

    public Season(Integer year, Integer month, Integer topResultsCount, SeasonStatus status, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.year = year;
        this.month = month;
        this.topResultsCount = (topResultsCount != null) ? topResultsCount : SeasonConstants.DEFAULT_TOP_RESULTS_COUNT;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Season(SeasonDto seasonDto) {
        this.id = seasonDto.getId();
        this.year = seasonDto.getYear();
        this.month = seasonDto.getMonth();
        this.topResultsCount = seasonDto.getTopResultsCount();
        this.status = seasonDto.getStatus();
        this.startedAt = seasonDto.getStartedAt();
        this.endedAt = seasonDto.getEndedAt();
    }

    public SeasonDto toDto() {
        return new SeasonDto(this);
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
        return Objects.equals(id, season.id) && Objects.equals(year, season.year) && Objects.equals(month, season.month) && Objects.equals(topResultsCount, season.topResultsCount) && status == season.status && Objects.equals(startedAt, season.startedAt) && Objects.equals(endedAt, season.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, topResultsCount, status, startedAt, endedAt);
    }
}
