@Deposit
Feature: Deposit functionality in Digital Bank application

  Scenario: As a User, I successfully deposit money into a new checking account
    Given I am logged into the Digital Bank home page with "ayana@gmail.com" and "Ayana123"
    When I create a new checking account with the following details:
      | accountType       | accountOwnership | accountName | initialDepositAmount |
      | Interest Checking | Joint            | Ayana       | 100                  |
    Then I navigate to the deposit page by selecting the account "Ayana (Interest Checking)"
    And I input the amount "200" into the "Deposit Amount" field
    When I click on the "Submit" button
    Then I should see the new balance on the "View Checking" page reflecting the deposit amount
    And I should see the operation saved in the history table with the appropriate message:
      | Operation Type | Category | Description          |
      |  Deposit       | Income   | (DPT) Online Deposit |
    And the Date, Amount, and Balance columns should be correctly updated











