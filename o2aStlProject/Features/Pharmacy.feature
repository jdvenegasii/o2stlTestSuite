Feature: Pharmacy

  Background: Below are the common steps for each scenario
    Given User Launch Browser
    When User opens URL "http://localhost:3000"
    And Click on Pharmacy Menu Item
    Then User can view Pharmacy page

  @Add_New_Pharmacy @Pharmacy
  Scenario Outline: Add a new Pharmacy
    And click on Add Pharmacy button
    Then User enters Pharmacy Name as "<PharmacyName>" and Address as "<Address>" and City as "<City>" and State as "<State>" and Country as "<Country>" and Zipcode as "<Zipcode>" and NPI as "<NPI>" and Latitude as "<Latitude>" and Longitude as "<Longitude>"
    And click on Create Pharmacy button
    Then User can view Pharmacy page
    And the created Pharmacy "<PharmacyName>" is now present in list
    And close browser

    Examples: 
      | PharmacyName         | Address                 | City             | State | Country | Zipcode | NPI        | Latitude    | Longitude    |
      | Genesis Pharmacy     | 1876 Craigshire Rd      | Maryland Heights | MO    | USA     |   63146 |  434837493 | 38.69233704 | -90.44738007 |
      | Walgreens            | 123 Lindell Blvd        | St. Louis        | MO    | USA     |   63108 |  383782947 |   38.643749 |   -90.264069 |
      | CVS                  | 7470 Manchester Rd      | Maplewood        | MO    | USA     |   63143 | 1983918393 |    38.61228 |    -90.32204 |
      | Dierbergs            | 8459 Eager Rd           | Richmond Heights | MO    | USA     |   63144 |  228473892 |    38.62922 |    -90.34006 |
      | Forest Park Pharmacy | 3535 S Jefferson Ave s1 | St. Louis        | MO    | USA     |   63118 |   93894839 |   38.597595 |   -90.203881 |

  @Cancel_Add_New_Pharmacy @Pharmacy
  Scenario: Cancel Add Pharmacy
    And click on Add Pharmacy button
    And User can view Add Pharmacy Page
    When User clicks cancel
    Then User can view Pharmacy page
    And close browser

  @Edit_Pharmacy @Pharmacy
  Scenario: Edit Pharmacy
    And User clicks Edit button for the Pharmacy "Genesis Pharmacy" in the list
    And User can view Edit Pharmacy Page
    Then User changes Pharmacy Name to "Genesis featuring Phil Collins"
    And User clicks on Submit Edit button
    Then User can view Pharmacy Page
    And the edited Pharmacy "Genesis featuring Phil Collins" is now present in list
    And close browser

  @Search_Pharmacy @Pharmacy
  Scenario Outline: Search for a Pharmacy by Zipcode
    And User enters "<Zipcode>"
    Then clicks Search button
    Then User should see the Pharmacy with "<Zipcode>" listed
    And close browser

    Examples: 
      | Zipcode |
      |   63141 |
      |   63017 |
      |      14 |
      |   63108 |
      |   63047 |

  @Reset_Pharmacy_List @Pharmacy
  Scenario: Reset Pharmacy List After Pharmacy Search
    And User enters "63017"
    Then clicks Search button
    Then User should see the Pharmacy with "63017" listed
    Then User clicks Reset List button
    And complete List of pharmacies is displayed
    And close browser

  @Delete_Pharmacy @Pharmacy
  Scenario Outline: Delete a Pharmacy
    And Delete Pharmacy with NPI as "<NPI>"
    Then Deleted Pharmacy with NPI as "<NPI>" should be removed from list
    And close browser

    Examples: 
      | NPI        |
      | 1983918393 |
      |   93894839 |
      |  383782947 |
      |  434837493 |
      |  228473892 |
