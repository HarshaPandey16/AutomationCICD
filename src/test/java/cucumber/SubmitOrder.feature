
@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Eccomerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and Submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name             | password  | productName  |
      | pihu16@gmail.com | Pihu@1234 | ZARA COAT 3  |
     
