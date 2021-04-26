Feature: Login


  @login
  Scenario: Successful Login with Valid Credentials
    Given User Launch Browser
    When User opens URL "http://localhost:3000/Login"
    And User enters Username and Password
    And Click on Login
    Then Page Title should be "Galaxe"
    And close browser

