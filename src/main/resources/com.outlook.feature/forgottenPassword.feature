@forgottenPassword
Feature: Forgotten Password
  As an approved user of the system
  I can to recover the password in different ways
  So that I get the forgotten password to the application

  @positive
  Scenario Outline: Verify the forgotten password functionality. There is a Captcha which couldn't be automated
    there are two options to workaround it. A third-party service and type it manually. I put a stub.
    Given I navigate to Login page
    When I recover the password using the following values:
      | Email    | <Email>    |
      | Reason   | <Reason>   |
    Then Captcha is displayed
    Examples:
      | Email                   | Reason                                |
      | geller.ross@outlook.com | I forgot my password                  |
      | geller.ross@outlook.com | I know my password, but can't sign in |







