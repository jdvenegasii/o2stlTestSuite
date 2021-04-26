package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchPharmacyPage {
	public WebDriver ldriver;
	WaitHelper waitHelper;

	public SearchPharmacyPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	@FindBy(how = How.XPATH, using = "/html//input[@id='user']")
	@CacheLookup
	WebElement txtZipcode;

	@FindBy(how = How.XPATH, using = "/html//div[@id='root']//form/button[3]")
	@CacheLookup
	WebElement btnSearchPharmacy;

	@FindBy(how = How.XPATH, using = "/html//div[@id='root']//table//td[6]")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table/tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table/tr/td")
	List<WebElement> tableColumns;

	public void setZipcode(String zip) {
		waitHelper.WaitForElement(txtZipcode, 30);
		txtZipcode.clear();
		txtZipcode.sendKeys(zip);
	}

	public void clickSearch() {
		btnSearchPharmacy.click();
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	public boolean searchListForPharmacy(String zip) {
		waitHelper.WaitForElement(table, 30);
		boolean flag;

		String tableZip = tblSearchResults.getText();

		if (tableZip.equals(zip)) {
			System.out.println("A Pharmacy with Zipcode " + zip + " was found");
			return flag = true;
		} else {
			System.out.println("A Pharmacy with Zipcode " + zip + " was not found");
			return flag = false;
		}

	}

}
