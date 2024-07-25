package com.kata.tennis.strategy;

import com.kata.tennis.ScoreCalculator;

public class DeuceScoreStrategy implements ScoreStrategy{
    @Override
    public void playerWinsBall(char player, ScoreCalculator scoreCalculator) {
        if (player == 'A') {
            scoreCalculator.handleAdvantageForPlayerA();
        } else {
            scoreCalculator.handleAdvantageForPlayerB();
        }

        if (scoreCalculator.isGameOver()) {
            scoreCalculator.setScoreStrategy(scoreCalculator.getGameOverScoreStrategy());
        }
    }
}
