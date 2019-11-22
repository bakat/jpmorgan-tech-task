Feature: An endpoint that allows to manage posts

  @positiveScenario
  Scenario: List all posts
    Given I want to get a list of all posts created
    When I submit my request
    Then The response should return with success
    And The response message should contain 100 posts
    And All posts should contain data populated

  @positiveScenario
  Scenario: Create a new post
    Given I want to create a new post with the title "The newest new post!"
    And the posts content is "Another post for testing..."
    And the posts userId is 1
    When I request the post creation
    Then The response should return with success
    And The response message should contain an id greater than a hundred
    And The response message should contain the data I sent in

  @positiveScenario
  Scenario: Filter post by id
    Given I want to filter the posts by the post id number 2
    When I submit my request
    Then The response should return with success
    And The response message should contain 1 results
    And The response message should contain the same data returned from the list of Posts

  @negativeScenario
  Scenario: Filter post by invalid id
    Given I want to filter the posts by the post id number 999
    When I submit my request
    Then The response message should return an error