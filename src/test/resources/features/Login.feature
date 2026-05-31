Feature: User login

  Scenario: Login succeeds with valid credentials
    Given a unique registered user exists
    When the user logs in with the registered credentials
    Then the feed page is displayed

  Scenario Outline: Login fails with invalid credentials
    Given a unique registered user exists
    When the user attempts to log in with email "<email>" and password "<password>"
    Then the login error message "Email or password was incorrect" is displayed

    Examples:
      | email                    | password          |
      | registered-user          | WrongPass123!     |
      | missing.user@example.com | StrongPass123!    |
