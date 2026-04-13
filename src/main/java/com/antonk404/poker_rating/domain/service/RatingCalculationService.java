package com.antonk404.poker_rating.domain.service;

import org.springframework.stereotype.Service;

@Service
public class RatingCalculationService {

    public int calcRegPoints(int position, int totalPlayers) {
        if (totalPlayers < 6 || position == totalPlayers) return 0;
        double fraction = (double) (totalPlayers - position) / totalPlayers;
        return (int) Math.round(Math.pow(fraction, 1.2) * (Math.log(totalPlayers) / Math.log(2)) * 100);
    }

    public int calcProPoints(int position, int totalPlayers) {
        if (totalPlayers < 6) return 0;
        if ((double) position / totalPlayers > 0.30) return 0;
        double percentile = (double) (totalPlayers - position) / (totalPlayers - 1);
        return (int) Math.round(500 * Math.pow(percentile, 2.5) * (Math.log(totalPlayers) / Math.log(2)));
    }

    public int calcBountyScore(int bountyBase, int victimProRating, double avgProRating) {
        double multiplier = avgProRating == 0 ? 1.0 : 1.0 + victimProRating / avgProRating;
        return (int) Math.round(bountyBase * multiplier);
    }
}
