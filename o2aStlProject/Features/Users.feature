Feature: Users

  @Add_New_User
  Scenario: Add a new User
    Given User Launch Browser
    When User opens URL "http://localhost:3000"
    And Click on Sign up Menu Item
    Then User can view Register page
    When User enter member info
    And click on Register button
    Then Page Title should be "Galaxe"
    And close browser



