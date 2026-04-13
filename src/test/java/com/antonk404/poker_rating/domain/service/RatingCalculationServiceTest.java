package com.antonk404.poker_rating.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RatingCalculationServiceTest {

    private RatingCalculationService service;

    @BeforeEach
    void setUp() {
        service = new RatingCalculationService();
    }

    // --- calcRegPoints ---

    @Test
    void calcRegPoints_lessThanSixPlayers_returnsZero() {
        assertThat(service.calcRegPoints(1, 5)).isZero();
    }

    @Test
    void calcRegPoints_lastPlace_returnsZero() {
        assertThat(service.calcRegPoints(10, 10)).isZero();
    }

    @Test
    void calcRegPoints_firstPlaceOutOfTen_returnsExpected() {
        // fraction = 9/10 = 0.9
        // round(0.9^1.2 * log2(10) * 100) = 293
        assertThat(service.calcRegPoints(1, 10)).isEqualTo(293);
    }

    @Test
    void calcRegPoints_middlePlace_returnsExpected() {
        // fraction = 5/10 = 0.5
        // round(0.5^1.2 * log2(10) * 100) = 145
        assertThat(service.calcRegPoints(5, 10)).isEqualTo(145);
    }

    // --- calcProPoints ---

    @Test
    void calcProPoints_lessThanSixPlayers_returnsZero() {
        assertThat(service.calcProPoints(1, 5)).isZero();
    }

    @Test
    void calcProPoints_outsideTopThirtyPercent_returnsZero() {
        // 4/10 = 0.4 > 0.30
        assertThat(service.calcProPoints(4, 10)).isZero();
    }

    @Test
    void calcProPoints_firstPlace_returnsExpected() {
        // percentile = 1.0
        // round(500 * 1.0^2.5 * log2(10)) = 1661
        assertThat(service.calcProPoints(1, 10)).isEqualTo(1661);
    }

    @Test
    void calcProPoints_exactlyAtThirtyPercentBoundary_returnsPoints() {
        // 3/10 = 0.30
        // percentile = 7/9 ≈ 0.778
        // round(500 * 0.778^2.5 * log2(10)) = 886
        assertThat(service.calcProPoints(3, 10)).isEqualTo(886);
    }

    // --- calcBountyScore ---

    @Test
    void calcBountyScore_avgProRatingIsZero_returnsBase() {
        assertThat(service.calcBountyScore(100, 0, 0.0)).isEqualTo(100);
    }

    @Test
    void calcBountyScore_victimStrongerThanAverage_returnsBonusAboveBase() {
        // multiplier = 1 + 200/100 = 3.0
        assertThat(service.calcBountyScore(100, 200, 100.0)).isEqualTo(300);
    }

    @Test
    void calcBountyScore_victimWeakerThanAverage_returnsBonusBelowDoubleBase() {
        // multiplier = 1 + 50/100 = 1.5
        assertThat(service.calcBountyScore(100, 50, 100.0)).isEqualTo(150);
    }
}
