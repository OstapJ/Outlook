@login
Feature: Login
  As an approved user of the system
  I can log in to the system with a username and password
  So that I have access to the relevant areas of the system

  Background:
    Given I navigate to Home page

  @positive
  Scenario Outline: User login with valid credentials
    When I resize the page according to the following device:
      | Device      | <Device>      |
      | Orientation | <Orientation> |
    Then Menu is displayed
    And Top navigation menu links isn't displayed

    Examples:
      | Device   | Orientation |
      | Iphone 6 | Portrait    |
      | Ipad     | Portrait    |
      | Nexus 6P | Portrait    |





