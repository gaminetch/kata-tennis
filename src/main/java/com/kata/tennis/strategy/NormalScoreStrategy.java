package com.kata.tennis.strategy;

import com.kata.tennis.ScoreCalculator;

public class NormalScoreStrategy implements ScoreStrategy{
    @Override
    public void playerWinsBall(char player, ScoreCalculator scoreCalculator) {
        if (player == 'A') {
            scoreCalculator.handlePlayerAScore();
        } else {
            scoreCalculator.handlePlayerBScore();
        }

        if (scoreCalculator.isDeuce()) {
            scoreCalculator.setScoreStrategy(scoreCalculator.getDeuceScoreStrategy());
        }
    }
}
