Feature: Positive Test Cases for Deposit functionality

  Scenario: Positive Test Cases for Savings
    Given user is on the Digital Bank sign in page
    And user logs in with  "ayana@gmail.com" and "Ayana123"
    When user creates a savings account with the following fields and valid data
      | savingAccountType | savingAccountOwnership | savingAccountName | initialSavingDeposits |
      | Savings           | Individual             | Olega             | 500                   |
    Then user successfully creates a savings account and navigates to the "Deposit" page
    When user selects the new account to deposit with the following amount
      | accountForDeposit | depositAmount |
      | Olega             | 300           |
    Then the user validates the new amount on the "View Savings Accounts" page
    And the user validates the operation is saved in the history table with the following details
      | Date             | Category | Description                      | Amount | Balance |
      | 2024-05-20 17:04 | Income   | 845328266 (DPT) - Online Deposit | 300    | 800     |
    And the user validates the Date, Amount, and Balance columns are correct
