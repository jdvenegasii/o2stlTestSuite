package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class AddPharmacyPage {

	public WebDriver ldriver;
	WaitHelper waitHelper;

	public AddPharmacyPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	@FindBy(how = How.XPATH, using = "//div[@id='basic-navbar-nav']//a[@href='/Pharmacy']")
	@CacheLookup
	WebElement getLnkAddPharmacy_menuitem;

	@FindBy(how = How.XPATH, using = "/html//div[@id='root']//form/button[3]")
	@CacheLookup
	WebElement btnAddPharmacy;

	@FindBy(how = How.CSS, using = "input#name")
	@CacheLookup
	WebElement txtPharmacyName;

	@FindBy(how = How.CSS, using = "input#address")
	@CacheLookup
	WebElement txtPharmacyAddress;

	@FindBy(how = How.CSS, using = "input#city")
	@CacheLookup
	WebElement txtPharmacyCity;

	@FindBy(how = How.CSS, using = "input#state")
	@CacheLookup
	WebElement txtPharmacyState;

	@FindBy(how = How.CSS, using = "input#country")
	@CacheLookup
	WebElement txtPharmacyCountry;

	@FindBy(how = How.CSS, using = "input#zipcode")
	@CacheLookup
	WebElement txtPharmacyZip;

	@FindBy(how = How.CSS, using = "input#npi")
	@CacheLookup
	WebElement txtNpi;

	@FindBy(how = How.CSS, using = "input#latitude")
	@CacheLookup
	WebElement txtLatitude;

	@FindBy(how = How.CSS, using = "input#longitude")
	@CacheLookup
	WebElement txtLongitude;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//form[@class='p-5']/button[@type='submit']")
	@CacheLookup
	WebElement btnCreatePharmacy;
	
	@FindBy(how = How.XPATH, using = "//div[@id='root']//form[@class='p-5']/button[@type='button']")
	@CacheLookup
	WebElement btnCancelPharmacy;

	@FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//th")
	List<WebElement> tableColumns;

	public void clickOnAddPharmacyMenuItem() {
		getLnkAddPharmacy_menuitem.click();
	}

	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void clickOnAddPharmacy() {
		btnAddPharmacy.click();
	}

	public void setPharmacyName(String pharmacyName) {
		waitHelper.WaitForElement(txtPharmacyName, 30);
		txtPharmacyName.clear();
		txtPharmacyName.sendKeys(pharmacyName);
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		waitHelper.WaitForElement(txtPharmacyAddress, 30);
		txtPharmacyAddress.clear();
		txtPharmacyAddress.sendKeys(pharmacyAddress);
	}

	public void setPharmacyCity(String pharmacyCity) {
		waitHelper.WaitForElement(txtPharmacyCity, 30);
		txtPharmacyCity.clear();
		txtPharmacyCity.sendKeys(pharmacyCity);
	}

	public void setPharmacyState(String pharmacyState) {
		waitHelper.WaitForElement(txtPharmacyState, 30);
		txtPharmacyState.clear();
		txtPharmacyState.sendKeys(pharmacyState);
	}

	public void setPharmacyCountry(String pharmacyCountry) {
		waitHelper.WaitForElement(txtPharmacyCountry, 30);
		txtPharmacyCountry.clear();
		txtPharmacyCountry.sendKeys(pharmacyCountry);
	}

	public void setPharmacyZip(String pharmacyZip) {
		waitHelper.WaitForElement(txtPharmacyZip, 30);
		txtPharmacyZip.clear();
		txtPharmacyZip.sendKeys(pharmacyZip);
	}

	public void setNpi(String pharmacyNpi) {
		waitHelper.WaitForElement(txtNpi, 30);
		txtNpi.clear();
		txtNpi.sendKeys(pharmacyNpi);
	}

	public void setLatitude(String latitude) {
		waitHelper.WaitForElement(txtLatitude, 30);
		txtLatitude.clear();
		txtLatitude.sendKeys(latitude);
	}

	public void setLongitude(String longitude) {
		waitHelper.WaitForElement(txtLongitude, 30);
		txtLongitude.clear();
		txtLongitude.sendKeys(longitude);
	}

	public void clickOnCreatePharmacy() {
		btnCreatePharmacy.click();
	}
	
	public void clickOnCancelAddPharmacy() {
		btnCancelPharmacy.click();
	}

	public void searchListForPharmacyName(String pharmacyName) {
		waitHelper.WaitForElement(table, 30);
		// Step 1: Find the column index
		System.out.println("Number of Table Columns: " + tableColumns.size());

		int columnIndex = 1;
		for (int i = 1; i < tableColumns.size(); i++) {
			System.out.println(tableColumns.get(i).getText());
			if ("Pharmacy Name".equalsIgnoreCase(tableColumns.get(i).getText())) {
				columnIndex = i + 1;
				break;
			}
		}
		System.out.println("Column Index : " + columnIndex);

		// Step 2: verify "pharmacyname" value for Pharmacy Name is in the table
		System.out.println("Number of rows : " + tableRows.size());

		boolean f = false;

		for (int i = 1; i <= tableRows.size(); i++) {
			String value = ldriver
					.findElement(
							By.xpath("//*[@id=\"root\"]/div/div/table/tbody/tr[" + i + "]/td[" + columnIndex + "]"))
					.getText();
			System.out.println(value);
			if (pharmacyName.equalsIgnoreCase(value)) {
				f = true;
				break;
			}
		}
		Assert.assertTrue("Pharmacy Name value verification", f);
	}

}
