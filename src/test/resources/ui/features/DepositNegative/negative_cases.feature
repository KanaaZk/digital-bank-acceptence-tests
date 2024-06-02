Feature: Negative Test Cases for Deposit Functionality

  Scenario: Deposit without selecting an account
    Given I am logged in as a registered user to the Digital bank home page
    When I navigate to the "Deposit" page and click the deposit button
    And I input 200 balance to the "Deposit Amount" field
    When I click the "Submit" button
    Then I should see an error message displaying "Please select an item in the list"

