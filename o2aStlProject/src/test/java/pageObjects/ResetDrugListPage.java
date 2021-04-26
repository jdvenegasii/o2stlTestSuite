package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class ResetDrugListPage {

	public WebDriver ldriver;
	public String button_xpath;
	WaitHelper waitHelper;

	public ResetDrugListPage(WebDriver rdriver) {
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

	@FindBy(how = How.CSS, using = "#root > div > div > div > div > form > button.m-3.btn.btn-secondary")
	@CacheLookup
	WebElement btnResetList;

	public void drugListReset() {
		waitHelper.WaitForElement(table, 30);
		System.out.println("Number of rows : " + tableRows.size());

		boolean f = false;

		if (tableRows.size() > 6) {
			f = true;
		}
		Assert.assertTrue("Drug List Reset Verification", f);
	}

	public void clickOnResetList() {
		btnResetList.click();
	}

}
