Feature: Drug

  Background: Below are the common steps for each scenario
    Given User Launch Browser
    When User opens URL "http://localhost:3000"
    And Click on Drug Menu Item
    Then User can view Drug page

  @Add_New_Drug @Drug
  Scenario Outline: Add a new Drug
    And click on Add Drug button
    Then User enters Drug Name as "<DrugName>" and NDC as "<NDC>" and Drug Strength as "<DrugStrength>" and GCN as "<GCN>" and Unit of Measurement as "<UnitOfMeasurement>" and Dosage as "<Dosage>"
    And click on Create Drug button
    Then User can view Drug page
    And the created Drug "<DrugName>" is now present in list
    And close browser

    Examples: 
      | DrugName   | NDC      | DrugStrength | GCN      | UnitOfMeasurement | Dosage |
      | Besponsa   | 00080100 |           15 | 80000010 |                50 |    100 |
      | Effexor XR | 00080833 |            7 | 80003380 |                25 |     60 |
      | Protonix   | 00080923 |           80 | 80003290 |                16 |     19 |
      | Xalatan    | 00138303 |           10 | 31003038 |                60 |     45 |
      | Cytotec    | 00251451 |            6 | 52001514 |                22 |     19 |

  @Cancel_Add_New_Drug @Drug
  Scenario: Cancel Add Drug
    And click on Add Drug button
    And User can view Add Drug Page
    When User clicks cancel Add Drug
    Then User can view Drug page
    And close browser
  
  @Edit_Drug @Drug
  Scenario: Edit Drug
    And User clicks Edit button for the Drug "Besponsa" in the list
    And User can view Edit Drug Page
    Then User changes Drug Name to "Happy Pill"
    And User clicks on Submit button
    Then User can view Drug Page
    And the edited Drug "Happy Pill" is now present in list
    And close browser

  @Delete_Drug @Drug
  Scenario Outline: Delete a Drug
    And Delete Drug with Name as "<DrugName>"
    Then Deleted Drug "<DrugName>" should be removed from list
    And close browser

    Examples: 
      | DrugName   |
      | Xalatan    |
      | Effexor XR |
      | Happy Pill |
      | Cytotec    |
      | Protonix   |

  @Search_Drug @Drug
  Scenario Outline: Search for a Drug by NDC
    And User enters "<NDC>"
    Then clicks Search button
    Then User should see the drug "<NDC>" listed
    And close browser

    Examples: 
      | NDC  |
      |  222 |
      | 1524 |
      |    1 |
      | 1533 |
      |  923 |
      
 	@Reset_Drug_List @Drug
 	Scenario: Reset Drug List After Drug Search
 		And User enters "1533"
 		Then clicks Search button
 		Then User should see the drug "1533" listed
 		Then User clicks Reset List button
 		And complete List of drugs is displayed
 		And close browser
