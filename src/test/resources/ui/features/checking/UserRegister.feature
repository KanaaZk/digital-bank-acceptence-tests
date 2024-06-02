Feature: User Registration

  Scenario: Register a new User Account
    Given the new user is on the sign up page
    When the user fills out all the required fields
      | title | firstName | lastName | gender | dateOfBirth | socialSecurityNumber | emailAddress    | password | confirmPassword | address        | locality      | region   | postalCode | country | homePhone     | mobilePhone    | workPhone     |
      | Mr.   | Jacob     | Mayson   | M      | 10/02/1978  | 133-54-5012          | jacob@gmail.com | Jacob123 | Jacob123        | Oak 102 Street | Silicon Oasis | Downtown | 00101      | UAE     | (645)747-4576 | (650)-535-5035 | (943)045-9283 |
    Then the user should see the success message