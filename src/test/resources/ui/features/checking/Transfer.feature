Feature: Transfer Between Accounts

  Scenario: Valid transfer between accounts
    Given a signed up user
    And the user has the following accounts:
      | accountName | initialDeposit |
      | Account1    | 1000           |
      | Account2    | 500            |
    When the user navigates to the "Transfer Between Accounts" page
    And the user transfers 200 from "Account1" to "Account2"
    Then the new amount in "Account2" should be 700
    And the operation should be saved in the history table with the appropriate message


  Scenario Outline: Validating transfer between different types of accounts
    Given a signed up user
    And the user has the following accounts:
      | accountName | initialDeposit |
      | <fromAccount> | <initialDeposit1> |
      | <toAccount> | <initialDeposit2> |
    When the user navigates to the "Transfer Between Accounts" page
    And the user selects "<fromAccount>" in the From selector
    And the user selects "<toAccount>" in the To selector
    And the user enters amount <amount>
    And the user clicks on the "Submit" button
    Then the transfer should be successful

    Examples:
      | initialDeposit1 | initialDeposit2 | fromAccount | toAccount | amount |
      | 1000            | 500             | Account1    | Account2  | 100    |
