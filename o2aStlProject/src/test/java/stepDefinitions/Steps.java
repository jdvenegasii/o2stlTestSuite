package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddDrugPage;
import pageObjects.AddPharmacyPage;
import pageObjects.AddUserPage;
import pageObjects.DeleteDrugPage;
import pageObjects.DeletePharmacyPage;
import pageObjects.EditDrugPage;
import pageObjects.EditPharmacyPage;
import pageObjects.LoginPage;
import pageObjects.ResetDrugListPage;
import pageObjects.ResetPharmacyListPage;
import pageObjects.SearchDrugPage;
import pageObjects.SearchPharmacyPage;
import pageObjects.SearchUserPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {
		logger = Logger.getLogger("Galaxe"); // added logger
		PropertyConfigurator.configure("Log4j.properties"); // added logger

		// Reading properties
		configProp = new Properties();
		// configProp=new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);

		String br = configProp.getProperty("browser");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
			logger.info("************* Launching Chrome Browser ********************");
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
			logger.info("************* Launching Firefox Browser ********************");
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
			logger.info("************* Launching IE Browser ********************");
		}
	}

	@Given("User Launch Browser")
	public void user_Launch_Browser() {
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("************* Opening URL ********************");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Username and Password")
	public void user_enters_Username_and_Password() {
		logger.info("************* Providing Login Details ********************");
		lp.setUserName(configProp.getProperty("Username"));
		lp.setPassword(configProp.getProperty("Password"));
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("************* Providing Login Details ********************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logger.info("************* Started Login ********************");
		lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) {

		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			// logger.info("************* Login Passed ********************");
			Assert.assertTrue(false);
		} else {
			// logger.info("************* Login Failed ********************");
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		logger.info("************* Click on Logout Link ********************");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() throws InterruptedException {
		logger.info("************* Closing Browser ********************");
		Thread.sleep(3000);
		driver.close();
	}

	// Customer feature step definitions

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		addCust = new AddUserPage(driver);
		Assert.assertEquals("Galaxe", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("Click on Sign up Menu Item")
	public void click_on_Sign_up_Menu_Item() throws InterruptedException {
		addCust = new AddUserPage(driver);
		Thread.sleep(3000);
		addCust.clickOnSignUpMenuItem();
	}

	@Then("User can view Register page")
	public void user_can_view_Register_page() {
		Assert.assertEquals("Galaxe", addCust.getPageTitle());
	}

	@When("User enter member info")
	public void user_enter_member_info() throws InterruptedException {
		logger.info("************* Adding New Member ********************");
		logger.info("************* Providing Member Details ********************");
		// String email=randomstring() + "@gmail.com";
		// addCust.setEmail(email);

		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		// addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setFirstName();
		addCust.setLastName();
		addCust.setDob("7/05/1985"); // Format: D/MM/YYYY
		addCust.setAddress1("123 Test Rd");
		addCust.setAddress2("Suite 1");
		addCust.setCity("Juniper");
		addCust.setState("MA");
		addCust.setUsername();
		addCust.setPw("test123");
		// addCust.setCompanyName("busyQA");
		// addCust.setAdminContent("This is for testing...........");
	}

	@When("click on Register button")
	public void click_on_Register_button() throws InterruptedException {
		logger.info("************* Saving Member Data ********************");
		addCust.clickOnRegister();
		Thread.sleep(2000);
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("************* Adding New Customer ********************");
		logger.info("************* Providing Customer Details ********************");
		String email = randomstring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName();
		addCust.setLastName();
		addCust.setDob("7/05/1985"); // Format: D/MM/YYYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing...........");

	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("************* Saving Customer Data ********************");
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}

	// steps for searching a customer using Email ID........
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
		logger.info("************* Searching Customer by Email ID ********************");
		searchCust = new SearchUserPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	// steps for searching a customer by using first name and lastname

	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("************* Searching Customer by Name ********************");
		searchCust = new SearchUserPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust = new SearchUserPage(driver);
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

	// steps for add pharmacy

	@When("Click on Pharmacy Menu Item")
	public void click_on_Pharmacy_Menu_Item() {
		logger.info("************* Clicking Pharmacy Link On Nav Bar  ********************");
		addPharm = new AddPharmacyPage(driver);
		addPharm.clickOnAddPharmacyMenuItem();
	}

	@Then("User can view Pharmacy page")
	public void user_can_view_Pharmacy_page() {
		logger.info("************* User Navigated to Drug Page ********************");
		addPharm = new AddPharmacyPage(driver);
		Assert.assertEquals("Galaxe", addPharm.getPageTitle());
	}

	@Then("click on Add Pharmacy button")
	public void click_on_Add_Pharmacy_button() {
		logger.info("************* Clicking Add Pharmacy Button ********************");
		addPharm.clickOnAddPharmacy();
	}

	@Then("User enters Pharmacy Name as {string} and Address as {string} and City as {string} and State as {string} and Country as {string} and Zipcode as {string} and NPI as {string} and Latitude as {string} and Longitude as {string}")
	public void user_enters_Pharmacy_Name_as_and_Address_as_and_City_as_and_State_as_and_Country_as_and_Zipcode_as_and_NPI_as_and_Latitude_as_and_Longitude_as(
			String string, String string2, String string3, String string4, String string5, String string6,
			String string7, String string8, String string9) {
		logger.info("************* Adding New Pharmacy ********************");
		logger.info("************* Providing Pharmacy Details ********************");

		addPharm.setPharmacyName(string);
		addPharm.setPharmacyAddress(string2);
		addPharm.setPharmacyCity(string3);
		addPharm.setPharmacyState(string4);
		addPharm.setPharmacyCountry(string5);
		addPharm.setPharmacyZip(string6);
		addPharm.setNpi(string7);
		addPharm.setLatitude(string8);
		addPharm.setLongitude(string9);
	}

	@Then("click on Create Pharmacy button")
	public void click_on_Create_Pharmacy_button() {
		logger.info("************* Saving Pharmacy Data ********************");
		addPharm.clickOnCreatePharmacy();
	}

	@Then("the created Pharmacy {string} is now present in list")
	public void the_created_Pharmacy_is_now_present_in_list(String string) {
		logger.info("************* Verifying Created Pharmacy is Now Listed ********************");
		addPharm.searchListForPharmacyName(string);
	}

	// Steps for cancel add pharmacy

	@Then("User can view Add Pharmacy Page")
	public void user_can_view_Add_Pharmacy_Page() {
		addPharm = new AddPharmacyPage(driver);
		logger.info("************* User Navigated to Add Pharmacy Page ********************");
		Assert.assertEquals("Galaxe", addPharm.getPageTitle());
	}

	@When("User clicks cancel")
	public void user_clicks_cancel() {
		logger.info("************* Clicking Cancel Add Pharmacy Button ********************");
		addPharm.clickOnCancelAddPharmacy();
	}

	// Steps for Search Pharmacy

	@Then("User should see the Pharmacy with {string} listed")
	public void user_should_see_the_Pharmacy_with_listed(String string) {
		logger.info("************* Verifying Pharmacy Searched for by Zipcode is Now Listed ********************");
		searchPharmacy = new SearchPharmacyPage(driver);
		searchPharmacy.searchListForPharmacy(string);
	}

	// Steps to Reset Pharmacy List

	@Then("complete List of pharmacies is displayed")
	public void complete_List_of_pharmacies_is_displayed() {
		resetPharmacyList = new ResetPharmacyListPage(driver);
		logger.info("************* Verifying Pharmacy List is Reset ********************");
		resetPharmacyList.pharmacyListReset();
	}

	// Steps to Edit Pharmacy

	@Then("User clicks Edit button for the Pharmacy {string} in the list")
	public void user_clicks_Edit_button_for_the_Pharmacy_in_the_list(String string) {
		logger.info("************* Locating Pharmacy to Edit in the List ********************");
		editPharmacy = new EditPharmacyPage(driver);
		editPharmacy.searchListForPharmacyName(string);
	}

	@Then("User can view Edit Pharmacy Page")
	public void user_can_view_Edit_Pharmacy_Page() {
		logger.info("************* User Navigated to Edit Pharmacy Page ********************");
		Assert.assertEquals("Galaxe", editPharmacy.getPageTitle());
	}

	@Then("User changes Pharmacy Name to {string}")
	public void user_changes_Pharmacy_Name_to(String string) {
		logger.info("************* Editing Pharmacy Name********************");
		editPharmacy.setPharmacyName(string);
	}

	@Then("User clicks on Submit Edit button")
	public void user_clicks_on_Submit_Edit_button() {
		logger.info("************* Submitting Pharmacy Edit ********************");
		editPharmacy.clickOnSubmitEdit();
	}

	@Then("User can view Pharmacy Page")
	public void user_can_view_Pharmacy_Page() {
		logger.info("************* User Navigated to Pharmacy Page ********************");
		Assert.assertEquals("Galaxe", editPharmacy.getPageTitle());
	}

	@Then("the edited Pharmacy {string} is now present in list")
	public void the_edited_Pharmacy_is_now_present_in_list(String string) {
		logger.info("************* Verifying Edited Pharmacy is in the List ********************");
		editPharmacy.searchListForPharmacyName(string);
	}

	// Steps for Delete Pharmacy

	@Then("Delete Pharmacy with NPI as {string}")
	public void delete_Pharmacy_with_NPI_as(String string) {
		logger.info("************* Locating then Deleting Specified Pharmacy From List ********************");
		deletePharm = new DeletePharmacyPage(driver);
		deletePharm.deletePharmacyWithNpi(string);
	}

	@Then("Deleted Pharmacy with NPI as {string} should be removed from list")
	public void deleted_Pharmacy_with_NPI_as_should_be_removed_from_list(String string) {
		logger.info("************* Verifying Deleted Pharmacy is Not Listed ********************");
		deletePharm.searchListForDeletedPharmacyNPI(string);
	}

	@Then("Click on Delete button")
	public void click_on_Delete_button() {
		logger.info("************* Deleting Pharmacy ********************");
		deletePharm.clickOnDeleteButton();
	}

	// Steps for Add Drug

	@When("Click on Drug Menu Item")
	public void click_on_Drug_Menu_Item() {
		logger.info("************* Clicking Drug Link On Nav Bar  ********************");
		addDrug = new AddDrugPage(driver);
		addDrug.clickOnDrugMenuItem();
	}

	@Then("User can view Drug page")
	public void user_can_view_Drug_page() {
		logger.info("************* User Navigated to Drug Page ********************");
		Assert.assertEquals("Galaxe", addDrug.getPageTitle());
	}

	@Then("click on Add Drug button")
	public void click_on_Add_Drug_button() {
		logger.info("************* Clicking Add Drug Button ********************");
		addDrug.clickOnAddDrug();
	}

	@Then("User enters Drug Name as {string} and NDC as {string} and Drug Strength as {string} and GCN as {string} and Unit of Measurement as {string} and Dosage as {string}")
	public void user_enters_Drug_Name_as_and_NDC_as_and_Drug_Strength_as_and_GCN_as_and_Unit_of_Measurement_as_and_Dosage_as(
			String string, String string2, String string3, String string4, String string5, String string6) {
		logger.info("************* Adding New Drug ********************");
		logger.info("************* Providing Drug Details ********************");

		addDrug.setDrugName(string);
		addDrug.setNationalDrugCode(string2);
		addDrug.setDrugStrength(string3);
		addDrug.setGenericCodeNumber(string4);
		addDrug.setUnitOfMeasurement(string5);
		addDrug.setDosage(string6);
	}

	@Then("click on Create Drug button")
	public void click_on_Create_Drug_button() {
		logger.info("************* Saving Drug Data ********************");
		addDrug.clickOnCreateDrug();
	}

	@Then("the created Drug {string} is now present in list")
	public void the_created_Drug_is_now_present_in_list(String string) {
		logger.info("************* Verifying Created Drug is Now Listed ********************");
		addDrug.searchListForDrugName(string);
	}

	// Steps for cancel add drug

	@Then("User can view Add Drug Page")
	public void user_can_view_Add_Drug_Page() {
		addDrug = new AddDrugPage(driver);
		logger.info("************* User Navigated to Add Drug Page ********************");
		Assert.assertEquals("Galaxe", addDrug.getPageTitle());
	}
	
	@When("User clicks cancel Add Drug")
	public void user_clicks_cancel_Add_Drug() {
		logger.info("************* Clicking Cancel Add Drug Button ********************");
		addDrug.clickOnCancelAddDrug();
	}

	// Steps for Edit Drug

	@Then("User clicks Edit button for the Drug {string} in the list")
	public void user_clicks_Edit_button_for_the_Drug_in_the_list(String string) {
		logger.info("************* Locating Drug to Edit in the List ********************");
		editDrug = new EditDrugPage(driver);
		editDrug.searchListForDrugName(string);
	}

	@Then("User can view Edit Drug Page")
	public void user_can_view_Edit_Drug_Page() {
		logger.info("************* User Navigated to Edit Drug Page ********************");
		Assert.assertEquals("Galaxe", editDrug.getPageTitle());
	}

	@Then("User changes Drug Name to {string}")
	public void user_changes_Drug_Name_to(String string) {
		logger.info("************* Editing Drug Name********************");
		editDrug.setDrugName(string);
	}

	@Then("User clicks on Submit button")
	public void user_clicks_on_Submit_button() {
		logger.info("************* Submitting Drug Edit ********************");
		editDrug.clickOnSubmitEdit();
	}

	@Then("User can view Drug Page")
	public void user_can_view_Drug_Page() {
		logger.info("************* User Navigated to Drug Page ********************");
		Assert.assertEquals("Galaxe", editDrug.getPageTitle());
	}

	@Then("the edited Drug {string} is now present in list")
	public void the_edited_Drug_is_now_present_in_list(String string) {
		logger.info("************* Verifying Edited Drug is in the List ********************");
		editDrug.searchListForDrugName(string);
	}

	// Steps for Delete Drug

	@Then("Delete Drug with Name as {string}")
	public void delete_Drug_with_Name_as(String string) {
		logger.info("************* Locating then Deleting Specified Drug From List ********************");
		deleteDrug = new DeleteDrugPage(driver);
		deleteDrug.find_Drug_with_Name(string);
	}

	@Then("Deleted Drug {string} should be removed from list")
	public void drug_should_be_removed_from_list(String string) {
		logger.info("************* Verifying Deleted Drug is Not Listed ********************");
		deleteDrug.searchListForDeletedDrugName(string);
	}

	// Steps for Drug Search

	@Then("User enters {string}")
	public void user_enters(String string) {
		logger.info("************* Entering Search Criteria ********************");
		searchDrug = new SearchDrugPage(driver);
		searchDrug.setNDC(string);
	}

	@Then("clicks Search button")
	public void clicks_Search_button() {
		logger.info("************* Initiating Search ********************");
		searchDrug.clickSearch();
	}

	@Then("User should see the drug {string} listed")
	public void user_should_see_the_drug_listed(String string) {
		logger.info("************* Verifying Search for Drug Successful ********************");
		boolean status = searchDrug.searchDrugByNDC(string);
		Assert.assertEquals(true, status);
	}

	// Steps to Reset Drug List

	@Then("User clicks Reset List button")
	public void user_clicks_Reset_List_button() {
		resetDrugList = new ResetDrugListPage(driver);
		logger.info("************* Clicking Reset Drug List Button ********************");
		resetDrugList.clickOnResetList();
	}

	@Then("complete List of drugs is displayed")
	public void complete_List_of_drugs_is_displayed() {
		logger.info("************* Verifying Drug List is Reset ********************");
		resetDrugList.drugListReset();
	}

}
