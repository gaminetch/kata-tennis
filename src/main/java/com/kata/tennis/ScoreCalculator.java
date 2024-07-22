package com.kata.tennis;

public class ScoreCalculator {

    private int playerAScore = 0;
    private int playerBScore = 0;
    private boolean playerAHasAdvantage = false;
    private boolean playerBHasAdvantage = false;
    private boolean gameOver = false;

    /**
     * Computes the score based on the sequence of ball wins.
     *
     * @param sequence a string containing 'A' and 'B' where 'A' means player A won the ball and 'B' means player B won the ball.
     */
    public void computeScore(String sequence) {
        for (char c : sequence.toCharArray()) {
            if (gameOver) {
                break;
            }
            if (c == 'A' || c == 'B') {
                playerWonBall(c);
            }
            if (!gameOver) {
                printScore();
            }
        }
    }

    /**
     * Updates the score and advantage based on which player won the ball.
     *
     * @param player a string "A" or "B" indicating which player won the ball.
     */
    private void playerWonBall(char player) {
        if (player == 'A') {
            handlePlayerAScore();
        } else if (player == 'B') {
            handlePlayerBScore();
        }
    }

    /**
     * Handles the score update for player A.
     */
    private void handlePlayerAScore() {
        if (isDeuce()) {
            handleAdvantageForPlayerA();
        } else if (playerAScore == 40) {
            declareWinner("Player A");
        } else {
            playerAScore = nextScore(playerAScore);
        }
    }

    /**
     * Handles the score update for player B.
     */
    private void handlePlayerBScore() {
        if (isDeuce()) {
            handleAdvantageForPlayerB();
        } else if (playerBScore == 40) {
            declareWinner("Player B");
        } else {
            playerBScore = nextScore(playerBScore);
        }
    }

    /**
     * Checks if the game is in a deuce state.
     *
     * @return true if both players have a score of 40, false otherwise.
     */
    private boolean isDeuce() {
        return playerAScore == 40 && playerBScore == 40;
    }

    /**
     * Handles the advantage logic for player A.
     */
    private void handleAdvantageForPlayerA() {
        if (playerAHasAdvantage) {
            declareWinner("Player A");
        } else if (playerBHasAdvantage) {
            playerBHasAdvantage = false;
        } else {
            playerAHasAdvantage = true;
        }
    }

    /**
     * Handles the advantage logic for player B.
     */
    private void handleAdvantageForPlayerB() {
        if (playerBHasAdvantage) {
            declareWinner("Player B");
        } else if (playerAHasAdvantage) {
            playerAHasAdvantage = false;
        } else {
            playerBHasAdvantage = true;
        }
    }

    /**
     * Declares the winner of the game and sets the game as over.
     *
     * @param winner the name of the player who won the game.
     */
    private void declareWinner(String winner) {
        System.out.println(winner + " wins the game");
        gameOver = true;
    }

    /**
     * Computes the next score for a player based on their current score.
     *
     * @param currentScore the current score of the player.
     * @return the next score of the player.
     */
    private int nextScore(int currentScore) {
        return switch (currentScore) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> currentScore;
        };
    }

    /**
     * Prints the current score of both players.
     */
    private void printScore() {
        if (playerAScore == 40 && playerBScore == 40) {
            if (playerAHasAdvantage) {
                System.out.println("Player A : Advantage / Player B : 40");
            } else if (playerBHasAdvantage) {
                System.out.println("Player A : 40 / Player B : Advantage");
            } else {
                System.out.println("Deuce");
            }
        } else {
            String score = String.format("Player A : %s / Player B : %s",
                    playerAScore, playerBScore);
            System.out.println(score);
        }
    }
}
