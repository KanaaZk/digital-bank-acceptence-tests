Feature: Amazon Country Selection

  Scenario: Login and change preferred country to UAE
    Given I am on the Amazon login page
    When I login with the following credentials
      | username               | password    |
      | kanaatkhan@gmail.com   | Aza96mat1$  |
    And I navigate to the country page
    And I click the country selection dropdown
    And I select "UAE" as my preferred country
    Then I should see "UAE" as the selected country