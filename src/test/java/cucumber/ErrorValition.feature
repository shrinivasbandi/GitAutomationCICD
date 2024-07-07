@tag
Feature: Error validation
  I want to use this template for my feature file


@ErrorValidation
Scenario Outline: Negative scenario for login
  Given landed on the Ecommerce page
  And Logged into the application using username <user> and password <password>
  Then "Incorrect email or password." message is displayed

   Examples: 
  
    | user                           | password        | 
    | 'shrinivasbandi5555@gmail.com' | '@Swarit101213' | 
