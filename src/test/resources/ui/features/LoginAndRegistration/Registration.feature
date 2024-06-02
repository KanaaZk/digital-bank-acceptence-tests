@Registration

Feature: Digital Bank Registration Page

  Background:
    Given The following user with "klara@gmail.com" is not in DB

    Given User navigates to Digital Bank signup page

  Scenario: Positive case. A sa user, I want to successfully create Digital Bank account
    When User creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email           | password | address        | locality  | region   | postalCode | country | homePhone     | mobilePhone    | workPhone     | termsCheckMark |
      | Mr.   | Klara     | James    | M      | 01/01/1992 | 195-82-0114 | klara@gmail.com | Klara123 | Oak 102 Street | Town City | Downtown | 45780      | UAE     | (645)747-4576 | (650)-535-5335 | (943)645-9283 | true           |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email           | password | address        | locality  | region   | postalCode | country | homePhone     | mobilePhone    | workPhone     | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Mr.   | Klara     | James    | M      | 01/01/1992 | 195-82-0114 | klara@gmail.com | Klara123 | Oak 102 Street | Town City | Downtown | 45780      | UAE     | (645)747-4576 | (650)-535-5335 | (943)645-9283 | true              | true             | true                  | true    |


  @NegativeRegistrationCases
  Scenario Outline: Negative Test Case. As a Digital Bank Admin I want to make sure users can not register without providing all valid data
    When User creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then the User should see the "<fieldWithError>" required field error message "<errorMessage>"


    Examples:
      | title | firstName | lastName  | gender | dob        | ssn         | email            | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckMark | fieldWithError | errorMessage                        |
      |       |           |           |        |            |             |                  |          |         |          |        |            |         |           |             |           |                | title          | Please select an item in the list.  |
      | Mr.   |           |           |        |            |             |                  |          |         |          |        |            |         |           |             |           |                | firstName      | Please fill out this field.         |
      | Mr.   | Grigory   |           |        |            |             |                  |          |         |          |        |            |         |           |             |           |                | lastName       | Please fill out this field.         |
      | Mr.   | Grigory   | Anton     |        |            |             |                  |          |         |          |        |            |         |           |             |           |                | gender         | Please select one of these options. |
      | Ms.   | Jane      | Alice     | F      |            |             |                  |          |         |          |        |            |         |           |             |           |                | dob            | Please fill out this field.         |
      | Ms.   | Ariana    | Grande    | F      | 01/05/1992 |             |                  |          |         |          |        |            |         |           |             |           |                | ssn            | Please fill out this field.         |
      | Mrs.  | Kelly     | Robertson | F      | 08/07/1985 | 123-07-1702 |                  |          |         |          |        |            |         |           |             |           |                | email          | Please fill out this field.         |
      | Mr.   | Taylor    | Baker     | M      | 08/07/1978 | 123-00-0709 | taylor@Gmail.com |          |         |          |        |            |         |           |             |           |                | password       | Please fill out this field.         |

