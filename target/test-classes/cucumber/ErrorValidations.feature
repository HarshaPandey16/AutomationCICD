
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Message Confirmation
    Given I landed on Eccomerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name             | password  |
      | pihu16@gmail.com | Pihu@12   |
