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

public class EditPharmacyPage {

	public WebDriver ldriver;
	public String button_xpath;
	WaitHelper waitHelper;

	public EditPharmacyPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	@FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//th")
	List<WebElement> tableColumns;

	@FindBy(how = How.CSS, using = "input#name")
	@CacheLookup
	WebElement txtPharmacyName;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//form[@class='p-5']/button[@type='submit']")
	@CacheLookup
	WebElement btnSubmitPharmacy;

	public void searchListForPharmacyName(String pharmacyname) {
		waitHelper.WaitForElement(table, 30);
		// Step 1: Find the column index
		System.out.println(tableColumns.size());

		int columnIndex = 1;
		for (int i = 1; i < tableColumns.size(); i++) {
			System.out.println(tableColumns.get(i).getText());
			if ("Pharmacy Name".equalsIgnoreCase(tableColumns.get(i).getText())) {
				columnIndex = i + 1;
				break;
			}
		}
		System.out.println("Column Index : " + columnIndex);

		// Step 2: verify "Pharmacy Name" value for pharmacyname is in the table
		System.out.println("Number of rows : " + tableRows.size());

		boolean f = false;

		for (int i = 1; i <= tableRows.size(); i++) {
			String value = ldriver
					.findElement(
							By.xpath("//*[@id=\"root\"]/div/div/table/tbody/tr[" + i + "]/td[" + columnIndex + "]"))
					.getText();
			System.out.println(value);
			if (pharmacyname.equalsIgnoreCase(value)) {
				f = true;
				ldriver.findElement(By.cssSelector(
						"#root > div > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(9) > button"))
						.click();
				break;
			}
		}
		Assert.assertTrue("Drug Name value verification", f);

	}

	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void setPharmacyName(String pharmacyname) {
		waitHelper.WaitForElement(txtPharmacyName, 30);
		txtPharmacyName.clear();
		txtPharmacyName.sendKeys(pharmacyname);
	}

	public void clickOnSubmitEdit() {
		btnSubmitPharmacy.click();
	}

}
