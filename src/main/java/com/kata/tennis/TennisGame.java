package com.kata.tennis;

public class TennisGame {


    /**
     * Main method to run the tennis game simulation.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ScoreCalculator game = new ScoreCalculator();
        game.computeScore("ABABABBABAAA");
    }
}
