Feature: An endpoint that allows to retrieve users and their details

  @positiveScenario
  Scenario: List all users
    Given I want to get a list of all users
    When I submit my request
    Then The response should return with success
    And The response message should contain 10 users
    And All users should contain data populated

  @positiveScenario
  Scenario: Filter users by id
    Given I want to filter the users by the id number 2
    When I submit my request
    Then The response should return with success
    And The response message should contain one result
    And The response message should contain the same data returned from the list of Users

  @negativeScenario
  Scenario: Filter user by invalid id
    Given I want to filter the users by the id number 999
    When I submit my request
    Then The response message should return an error