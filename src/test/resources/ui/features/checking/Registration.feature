Feature: Registration

  Scenario: Register a new personal account
    Given the user is on the sign up page
    When the user fills out all the require fields
      | title | firstName | lastName | gender | dateOfBirth | socialSecurityNumber | emailAddress      | password   | confirmPassword | address        | locality  | region   | postalCode | country | homePhone     | mobilePhone    | workPhone     |
      | Mr.   | Grigory   | Anthony  | M      | 01/01/1992  | 133-54-5661          | grigory@gmail.com | Grigory123 | Grigory123      | Oak 102 Street | Town City | Downtown | 45780      | UAE     | (645)747-4576 | (650)-535-5335 | (943)645-9283 |
    Then the user should see the success messageAddress
