package com.antonk404.poker_rating.entity.dto;

import com.antonk404.poker_rating.entity.constant.SeasonConstants;
import com.antonk404.poker_rating.entity.Season;
import com.antonk404.poker_rating.entity.enums.SeasonStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SeasonDto {

    private UUID id;
    private Integer year;
    private Integer month;
    private Integer topResultsCount;
    private SeasonStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public SeasonDto(Integer year, Integer month, Integer topResultsCount, SeasonStatus status, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.year = year;
        this.month = month;
        this.topResultsCount = (topResultsCount != null) ? topResultsCount : SeasonConstants.DEFAULT_TOP_RESULTS_COUNT;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public SeasonDto(Season seasonEntity) {
        this.id = seasonEntity.getId();
        this.year = seasonEntity.getYear();
        this.month = seasonEntity.getMonth();
        this.topResultsCount = seasonEntity.getTopResultsCount();
        this.status = seasonEntity.getStatus();
        this.startedAt = seasonEntity.getStartedAt();
        this.endedAt = seasonEntity.getEndedAt();
    }

    public Season toEntity() {
        return new Season(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTopResultsCount() {
        return topResultsCount;
    }

    public void setTopResultsCount(Integer topResultsCount) {
        this.topResultsCount = topResultsCount;
    }

    public SeasonStatus getStatus() {
        return status;
    }

    public void setStatus(SeasonStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeasonDto seasonDto = (SeasonDto) o;
        return Objects.equals(id, seasonDto.id) && Objects.equals(year, seasonDto.year) && Objects.equals(month, seasonDto.month) && Objects.equals(topResultsCount, seasonDto.topResultsCount) && status == seasonDto.status && Objects.equals(startedAt, seasonDto.startedAt) && Objects.equals(endedAt, seasonDto.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, topResultsCount, status, startedAt, endedAt);
    }
}
