# TennisGame

The `TennisGame` class implements a simple tennis score computer, handling the logic of a tennis scoring system. This project demonstrates the rules of tennis scoring, including handling scores, advantage, and deuce situations.

## Features

- Computes the score based on a sequence of ball wins.
- Handles the scoring rules of tennis including deuce and advantage.
- Prints the score after each ball win.
- Declares the winner of the game.

## Rules of Tennis Scoring

1. Each player starts with 0 points.
2. The first point won gives the player 15 points, the second 30 points, and the third 40 points.
3. If both players reach 40 points, the score is "deuce".
4. From deuce, a player must win two consecutive points to win the game:
   - The first point from deuce gives the player "advantage".
   - If the player with the advantage wins the next point, they win the game.
   - If the player without the advantage wins the point, the score returns to deuce.

### HOW TO RUN

Locate the TennisGame class and run the main method with the appropriate input

