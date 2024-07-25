package com.kata.tennis;

import com.kata.tennis.strategy.DeuceScoreStrategy;
import com.kata.tennis.strategy.GameOverScoreStrategy;
import com.kata.tennis.strategy.NormalScoreStrategy;
import com.kata.tennis.strategy.ScoreStrategy;

public class ScoreCalculator {
    private ScoreStrategy normalScoreStrategy;
    private ScoreStrategy deuceScoreStrategy;
    private ScoreStrategy gameOverScoreStrategy;
    private ScoreStrategy currentScoreStrategy;

    private int playerAScore = 0;
    private int playerBScore = 0;
    private boolean playerAHasAdvantage = false;
    private boolean playerBHasAdvantage = false;

    /**
     * Constructor to initialize the score strategies.
     * Normal score strategy is set as the current score strategy.
     */
    public ScoreCalculator() {
        normalScoreStrategy = new NormalScoreStrategy();
        deuceScoreStrategy = new DeuceScoreStrategy();
        gameOverScoreStrategy = new GameOverScoreStrategy();
        currentScoreStrategy = normalScoreStrategy;
    }

    /**
     * Method to compute the score based on the sequence of wins by players.
     * @param sequence
     */
    public void computeScore(String sequence) {
        for (char c : sequence.toCharArray()) {
            currentScoreStrategy.playerWinsBall(c, this);
            if (!isGameOver()) {
                printScore();
            }
        }
    }

    /**
     * Method to handle the score when player A wins a ball.
     * If player A wins the ball and has a score of 40, then player A is declared as the winner.
     */
    public void handlePlayerAScore() {
        if (playerAScore == 40) {
            declareWinner("Player A");
        } else {
            playerAScore = nextScore(playerAScore);
        }
    }

    /**
     * Method to handle the score when player B wins a ball.
     * If player B wins the ball and has a score of 40, then player B is declared as the winner.
     */
    public void handlePlayerBScore() {
        if (playerBScore == 40) {
            declareWinner("Player B");
        } else {
            playerBScore = nextScore(playerBScore);
        }
    }

    /**
     * Method to handle the advantage for player A.
     */
    public void handleAdvantageForPlayerA() {
        if (playerAHasAdvantage) {
            declareWinner("Player A");
        } else if (playerBHasAdvantage) {
            playerBHasAdvantage = false;
        } else {
            playerAHasAdvantage = true;
        }
    }

    /**
     * Method to handle the advantage for player B.
     */
    public void handleAdvantageForPlayerB() {
        if (playerBHasAdvantage) {
            declareWinner("Player B");
        } else if (playerAHasAdvantage) {
            playerAHasAdvantage = false;
        } else {
            playerBHasAdvantage = true;
        }
    }

    /**
     * Method to check if the score is deuce.
     * @return boolean
     */
    public boolean isDeuce() {
        return playerAScore == 40 && playerBScore == 40;
    }

    public boolean isAdvantageForPlayerA() {
        return playerAHasAdvantage;
    }

    public boolean isAdvantageForPlayerB() {
        return playerBHasAdvantage;
    }

    public boolean isGameOver() {
        return currentScoreStrategy == gameOverScoreStrategy;
    }

    public void declareWinner(String winner) {
        System.out.println(winner + " wins the game");
        currentScoreStrategy = gameOverScoreStrategy;
    }

    /**
     * Method to get the next score based on the current score.
     * @param currentScore
     * @return int
     */
    public int nextScore(int currentScore) {
        return switch (currentScore) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> currentScore;
        };
    }

    /**
     * Method to print the score.
     */
    public void printScore() {
        if (playerAScore == 40 && playerBScore == 40) {
            if (playerAHasAdvantage) {
                System.out.println("Player A : Advantage / Player B : 40");
            } else if (playerBHasAdvantage) {
                System.out.println("Player A : 40 / Player B : Advantage");
            } else {
                System.out.println("Deuce");
            }
        } else {
            String score = String.format("Player A : %s / Player B : %s", playerAScore, playerBScore);
            System.out.println(score);
        }
    }

    public void setScoreStrategy(ScoreStrategy strategy) {
        currentScoreStrategy = strategy;
    }

    public ScoreStrategy getNormalScoreStrategy() {
        return normalScoreStrategy;
    }

    public ScoreStrategy getDeuceScoreStrategy() {
        return deuceScoreStrategy;
    }

    public ScoreStrategy getGameOverScoreStrategy() {
        return gameOverScoreStrategy;
    }
}
