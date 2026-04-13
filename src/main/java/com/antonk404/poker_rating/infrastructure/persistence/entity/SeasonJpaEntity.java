package com.antonk404.poker_rating.infrastructure.persistence.entity;

import com.antonk404.poker_rating.domain.model.enums.SeasonStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "seasons")
public class SeasonJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(name = "top_results_count", nullable = false)
    private Integer topResultsCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonStatus status;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public SeasonJpaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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
}
