Feature: Feed posting

  Scenario: Create a new post on the feed
    Given the registered user is logged in
    When the user creates a feed post with text "Cucumber homework post"
    Then the feed contains the new post
