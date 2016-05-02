@login
Feature: Login
  As an approved user of the system
  I can log in to the system with a username and password
  So that I have access to the relevant areas of the system

  @positive
  @logout
  Scenario: User login with valid credentials
    When I login with the following user credentials:
      | Email    | geller.ross@outlook.com |
      | Password | Password1!$$            |
    Then Mail Outlook page is displayed

  @positive
  @logout
  Scenario: Verify that user credentials are hold on when Keep me signed in functionality is used
    Given I navigate to Login page
    And I fill the following user credentials:
      | Email    | geller.ross@outlook.com |
      | Password | Password1!$$            |
    When I select to keep me signed in
    And I click log in
    Then Mail Outlook page is displayed
    When I reopen the browser
    When I navigate to Login page
    Then Login page is displayed

  @negative
  Scenario Outline: Verify login with with incorrect credentials
    Given I navigate to Login page
    And I fill the following user credentials:
      | Email    | <Email>    |
      | Password | <Password> |
    When I click log in
    Then I should see "<Warning Message>" message displayed
    Examples:
      | Email                   | Password     | Warning Message                                                                           |
      | robin@outlook.com       | Password1!$$ | That Microsoft account doesn't exist. Enter a different account or get a new one.         |
      | geller.ross@outlook.com | Password1    | That password is incorrect. Be sure you're using the password for your Microsoft account. |












