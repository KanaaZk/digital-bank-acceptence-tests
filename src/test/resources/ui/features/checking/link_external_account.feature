Feature: Link External Account Functionality

  # This feature covers the scenarios related to linking external accounts in the DBank application.
  # Positive and negative test cases are included to ensure comprehensive coverage.

  @external_account
  Scenario: Register a new user and login
    Given I navigate to the registration page
    When I register with valid details
    Then I should see the account dashboard

  @external_account
  Scenario: Navigate to "External" > "Link External Account"
    Given I am logged in as a registered user
    When I navigate to the "External" section
    And I click on "Link External Account"
    Then I should see an error message indicating the absence of other bank providers

  @external_account @negative
  Scenario: Attempt to link an external account without filling any fields
    Given I am on the "Link External Account" page
    When I attempt to submit the form without filling any fields
    Then I should see error messages for all mandatory fields

  @external_account @negative
  Scenario Outline: Attempt to link an external account with invalid data
    Given I am on the "Link External Account" page
    When I select a valid bank provider
    And I enter "<username>" as the username
    And I enter "<password>" as the password
    And I submit the form
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username        | password   | error_message                      |
      | jess!           | Jess90     | invalid credentials                |
      |                 | Jessica123 | username is required               |
      | jessa@gmail.com |            | password is required               |
      |                 |            | username and password are required |

  @external_account @positive
  Scenario: Successfully link an external account
    Given I am on the "Link External Account" page
    When I select a valid bank provider
    And I enter valid credentials:
      | username        | password   |
      | jessa@gmail.com | Jessica123 |
    And I submit the form
    Then I should see a success message indicating the account has been linked
