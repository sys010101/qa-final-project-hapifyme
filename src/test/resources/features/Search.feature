Feature: User search and profile access

  Scenario: Search for a user and open the profile
    Given the registered user is logged in
    When the user searches for the registered user
    Then the search results include the registered user
    When the user opens the registered user's profile from the search results
    Then the registered user's profile page is displayed
