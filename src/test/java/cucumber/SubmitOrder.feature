@tag
Feature: Purchase the item online from Ecommerce website
  I want to use this template for my feature file

Background: 
  Given landed on Ecommerce page

@tag
Scenario Outline: Positive case of Submitting the order
  Given Logged into application using username <user> and password <password>
  When I added the product <product> on cart
  And Checkout <product> and submit the order
  Then 'THANKYOU FOR THE ORDER.' message is displayed on confirmation page.

  Examples: 
  
    | user                       | password        | product       |
    | 'shrinivasbandi@gmail.com' | '@Swarit101213' | 'ZARA COAT 3' |
    | 'sbandi1979@gmail.com'     | '@Chinna101213' | 'ZARA COAT 3' |

