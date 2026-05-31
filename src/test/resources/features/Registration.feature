Feature: User registration

  Scenario: Register a new user with valid data
    Given the user opens the registration form
    When the user registers with the following data:
      | firstName | Radu            |
      | lastName  | Cucumber        |
      | password  | StrongPass123!  |
    Then a registration success message is displayed
