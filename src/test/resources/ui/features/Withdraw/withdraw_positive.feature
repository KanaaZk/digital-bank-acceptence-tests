Feature: Withdraw functionality

  Background:
    Given the user is on the DBank sign-in page
    And the user signs in with "selena@gmail.com" and "Selena123"

  Scenario: Valid withdrawal from a new savings account
    When the user creates a new savings account with the following fields and valid data
      | savingAccountType | savingAccountOwnership | savingAccountName | initialSavingDeposits |
      | Savings           | Individual             | Grigory             | 5000                  |
    Then the user creates the savings account and navigates to the "Withdraw" page
    When the user selects the account for withdrawal with the following amount
      | accountForWithdraw | withdrawAmount |
      | Grigory            | 2000           |
    Then the user validates the new balance on the "View Savings Accounts" page is 3000
    And the user validates the operation is saved in the history table with details
      | Date             | Category   | Description       | Amount | Balance |
      | 2024-05-20 17:04 | Withdrawal | Online Withdrawal | 2000   | 3000    |


