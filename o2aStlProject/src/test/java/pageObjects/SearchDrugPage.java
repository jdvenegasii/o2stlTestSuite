package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.temporal.WeekFields;
import java.util.List;

public class SearchDrugPage {
	public WebDriver ldriver;
	WaitHelper waitHelper;

	public SearchDrugPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	@FindBy(how = How.XPATH, using = "/html//input[@id='user']")
	@CacheLookup
	WebElement txtNDC;

	@FindBy(how = How.XPATH, using = "/html//div[@id='root']//form/button[@type='submit']")
	@CacheLookup
	WebElement btnSearchDrug;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table/tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table/tr/td")
	List<WebElement> tableColumns;

	public void setNDC(String ndc) {
		waitHelper.WaitForElement(txtNDC, 30);
		txtNDC.clear();
		txtNDC.sendKeys(ndc);
	}

	public void clickSearch() {
		btnSearchDrug.click();
		waitHelper.WaitForElement(btnSearchDrug, 30);
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	public boolean searchDrugByNDC(String ndc) {
		boolean flag;

		String tableNDC = table.findElement(By.xpath("/html//div[@id='root']//table/tbody/tr/td[1]")).getText();

		if (tableNDC.equals(ndc)) {
			System.out.println("Drug with NDC " + ndc + " was found");
			return flag = true;
		} else {
			System.out.println("Drug with NDC " + ndc + " was not found");
			return flag = false;
		}

	}

}
