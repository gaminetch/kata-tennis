package com.kata.tennis;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ScoreCalculatorSteps {
    private ScoreCalculator scoreCalculator;
    private ByteArrayOutputStream outContent;

    @Given("a new tennis game")
    public void a_new_tennis_game() {
        scoreCalculator = new ScoreCalculator();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @When("the sequence of ball wins is {string}")
    public void the_sequence_of_ball_wins_is(String sequence) {
        scoreCalculator.computeScore(sequence);
    }

    @Then("the score should be {string}")
    public void the_score_should_be(String expectedScore) {
        String[] lines = outContent.toString().split(System.lineSeparator());
        String actualScore = lines[lines.length - 1];  // Last printed line
        assertEquals(expectedScore, actualScore);
    }
}
