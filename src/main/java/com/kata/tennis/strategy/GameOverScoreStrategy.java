package com.kata.tennis.strategy;

import com.kata.tennis.ScoreCalculator;

public class GameOverScoreStrategy implements ScoreStrategy{
    @Override
    public void playerWinsBall(char player, ScoreCalculator scoreCalculator) {
        // Game is over, no further action needed.
    }
}
