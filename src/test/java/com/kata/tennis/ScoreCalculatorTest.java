package com.kata.tennis;

import static org.junit.jupiter.api.Assertions.*;

import com.kata.tennis.ScoreCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ScoreCalculatorTest {

    private ScoreCalculator game;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        game = new ScoreCalculator();
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testInitialScore() {
        game.computeScore("");
        assertEquals("", normalizeOutput(outContent.toString().trim()));
    }

    @Test
    void testPlayerAWinsFirstBall() {
        game.computeScore("A");
        assertEquals("Player A : 15 / Player B : 0", normalizeOutput(outContent.toString().trim()));
    }

    @Test
    void testPlayerBWinsFirstBall() {
        game.computeScore("B");
        assertEquals("Player A : 0 / Player B : 15", normalizeOutput(outContent.toString().trim()));
    }

    @Test
    void testBothPlayersWinOneBallEach() {
        game.computeScore("AB");
        assertEquals("Player A : 15 / Player B : 0\nPlayer A : 15 / Player B : 15", normalizeOutput(outContent.toString().trim()));
    }

    @Test
    void testPlayerAWinsGameWithoutDeuce() {
        game.computeScore("AAAA");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player A wins the game"));
    }

    @Test
    void testPlayerBWinsGameWithoutDeuce() {
        game.computeScore("BBBB");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player B wins the game"));
    }

    @Test
    void testDeuceScenario() {
        game.computeScore("ABABAB");
        assertTrue(normalizeOutput(outContent.toString()).contains("Deuce"));
    }

    @Test
    void testPlayerAGetsAdvantage() {
        game.computeScore("ABABABA");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player A : Advantage / Player B : 40"));
    }

    @Test
    void testPlayerBGetsAdvantage() {
        game.computeScore("ABABABB");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player A : 40 / Player B : Advantage"));
    }

    @Test
    void testPlayerAWinsAfterAdvantage() {
        game.computeScore("ABABABAA");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player A wins the game"));
    }

    @Test
    void testPlayerBWinsAfterAdvantage() {
        game.computeScore("ABABABBB");
        assertTrue(normalizeOutput(outContent.toString()).contains("Player B wins the game"));
    }

    @Test
    void testBackToDeuceFromAdvantage() {
        game.computeScore("ABABABAB");
        assertTrue(normalizeOutput(outContent.toString()).contains("Deuce"));
    }

    /**
     * Normalizes the output by replacing platform-specific line separators with a standard "\n".
     * Some cases were failing due to line separators
     * @param output the output string to normalize.
     * @return the normalized output string.
     */
    private String normalizeOutput(String output) {
        return output.replace(System.lineSeparator(), "\n");
    }
}
