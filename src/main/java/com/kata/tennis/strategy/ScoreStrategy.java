package com.kata.tennis.strategy;

import com.kata.tennis.ScoreCalculator;

public interface ScoreStrategy {
    void playerWinsBall(char player, ScoreCalculator scoreCalculator);
}
