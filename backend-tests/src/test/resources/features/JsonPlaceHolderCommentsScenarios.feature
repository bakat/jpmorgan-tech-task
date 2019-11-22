Feature: An endpoint that allows to retrieve comments from posts

  @positiveScenario
  Scenario: List all comments
    Given I want to get a list of all comments posted
    When I submit my request
    Then The response should return with success
    And The response message should contain 500 comments
    And All comments should contain data populated

  @positiveScenario
  Scenario: Create a new comment
    Given I want to comment on a post with the id 1
    And the comments name is "This post is awesome!"
    And the comments email is "backendtests@jpmorgan.ie"
    And the comments content is "What a nice day to comment on a post!"
    When I request the creation of my comment
    Then The response should return with success
    And The response message should contain an id greater than five hundred
    And The response message should contain the comment data

  @positiveScenario
  Scenario: Filter comments by postId
    Given I want to filter the comments by the post id number 1
    When I submit my request
    Then The response should return with success
    And The response message should contain five results
    And The response message should contain the same data returned from the list of Comments

  @positiveScenario
  Scenario: Filter comments by invalid postId
    Given I want to filter the comments by the post id number 9999
    When I submit my request
    Then The response should return with success
    And The response message should not contain any results