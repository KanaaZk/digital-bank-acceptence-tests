Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "ayana@gmail.com" "Ayana123"
    When the user creates a new checking account with the following data
      | checkingAccountType | accountOwnership | accountName             | initialDepositAmount |
      | Standard Checking   | Individual       | Ayana's Second Checking | 100000.0             |
    Then the user should see the green "Successfully created new Standard Checking account named Ayana's Second Checking"
    And the user should see newly added account card
      | accountName             | accountType       | ownership  | accountNumber | InterestRate | balance   |
      | Ayana's Second Checking | Standard Checking | Individual | 486135958     | 0.0%         | 100000.00 |
    And the user should see the following transactions
      | date       | category | description               | amount   | balance |
      | 2024-04-24 | Income   | 845327719 (DPT) - Deposit | 100000.0 | 100000.0 |
