Feature: Create Checking Account

  Scenario: Attempt to create a new checking account with invalid details
    Given the user logs into the app using their credentials
      | username        | password |
      | ayana@gmail.com | Ayana123 |
    When the user attempts to create a new checking account with invalid details:
      | accountType       | ownership  | accountName | depositAmount |
      | Standard Checking | Individual | Ayana       | 20.00         |
    Then the user should see clear message indicating incorrect format
    And the user should remain on the same page to correct the errors
