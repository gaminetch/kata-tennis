Feature: Tennis Game Score Calculation

  Scenario: Player A wins a game without deuce
    Given a new tennis game
    When the sequence of ball wins is "AAAA"
    Then the score should be "Player A wins the game"

  Scenario: Deuce and Player A wins
    Given a new tennis game
    When the sequence of ball wins is "ABABABABAA"
    Then the score should be "Player A wins the game"

  Scenario: Deuce and Player B wins
    Given a new tennis game
    When the sequence of ball wins is "ABABABABBB"
    Then the score should be "Player B wins the game"

  Scenario: Normal scoring
    Given a new tennis game
    When the sequence of ball wins is "AABB"
    Then the score should be "Player A : 30 / Player B : 30"
