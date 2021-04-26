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

public class AddDrugPage {

	public WebDriver ldriver;
	WaitHelper waitHelper;

	public AddDrugPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	@FindBy(how = How.XPATH, using = "//div[@id='basic-navbar-nav']//a[@href='/Drug']")
	@CacheLookup
	WebElement getLnkDrug_menuitem;

	@FindBy(how = How.XPATH, using = "/html//div[@id='root']//form/button[3]")
	@CacheLookup
	WebElement btnAddDrug;
	
	@FindBy(how = How.XPATH, using = "//div[@id='root']//form[@class='p-5']/button[@type='button']")
	@CacheLookup
	WebElement btnCancel;

	@FindBy(how = How.CSS, using = "input#drugName")
	@CacheLookup
	WebElement txtDrugName;

	@FindBy(how = How.CSS, using = "input#nationalDrugCode")
	@CacheLookup
	WebElement txtNDC;

	@FindBy(how = How.CSS, using = "input#drugStrength")
	@CacheLookup
	WebElement txtDrugStrength;

	@FindBy(how = How.CSS, using = "input#genericCodeNum")
	@CacheLookup
	WebElement txtGCN;

	@FindBy(how = How.CSS, using = "input#unitOfMeasurement")
	@CacheLookup
	WebElement txtUnitOfMeasurement;

	@FindBy(how = How.CSS, using = "input#dosage")
	@CacheLookup
	WebElement txtDosage;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//form[@class='p-5']/button[@type='submit']")
	@CacheLookup
	WebElement btnCreateDrug;

	@FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//th")
	List<WebElement> tableColumns;
	

	public void clickOnDrugMenuItem() {
		waitHelper.WaitForElement(getLnkDrug_menuitem, 30);
		getLnkDrug_menuitem.click();
	}

	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void clickOnAddDrug() {
		btnAddDrug.click();
	}
	
	public void clickOnCancelAddDrug() {
		btnCancel.click();
	}

	public void setDrugName(String drugName) {
		waitHelper.WaitForElement(txtDrugName, 30);
		txtDrugName.clear();
		txtDrugName.sendKeys(drugName);
	}

	public void setNationalDrugCode(String ndc) {
		waitHelper.WaitForElement(txtNDC, 30);
		txtNDC.clear();
		txtNDC.sendKeys(ndc);
	}

	public void setDrugStrength(String drugStrength) {
		waitHelper.WaitForElement(txtDrugStrength, 30);
		txtDrugStrength.clear();
		txtDrugStrength.sendKeys(drugStrength);
	}

	public void setGenericCodeNumber(String gcn) {
		waitHelper.WaitForElement(txtGCN, 30);
		txtGCN.clear();
		txtGCN.sendKeys(gcn);
	}

	public void setUnitOfMeasurement(String uom) {
		waitHelper.WaitForElement(txtUnitOfMeasurement, 30);
		txtUnitOfMeasurement.clear();
		txtUnitOfMeasurement.sendKeys(uom);
	}

	public void setDosage(String dosage) {
		waitHelper.WaitForElement(txtDosage, 30);
		txtDosage.clear();
		txtDosage.sendKeys(dosage);
	}

	public void clickOnCreateDrug() {
		btnCreateDrug.click();
	}

	public void searchListForDrugName(String drugname) {
		waitHelper.WaitForElement(table, 30);
		// Step 1: Find the column index
		System.out.println("Number of Table Columns: " + tableColumns.size());

		int columnIndex = 1;
		for (int i = 1; i < tableColumns.size(); i++) {
			System.out.println(tableColumns.get(i).getText());
			if ("Drug Name".equalsIgnoreCase(tableColumns.get(i).getText())) {
				columnIndex = i + 1;
				break;
			}
		}
		System.out.println("Column Index : " + columnIndex);

		// Step 2: verify "drugname" value for Drug Name is in the table
		System.out.println("Number of rows : " + tableRows.size());

		boolean f = false;

		for (int i = 1; i <= tableRows.size(); i++) {
			String value = ldriver
					.findElement(
							By.xpath("//*[@id=\"root\"]/div/div/table/tbody/tr[" + i + "]/td[" + columnIndex + "]"))
					.getText();
			System.out.println(value);
			if (drugname.equalsIgnoreCase(value)) {
				f = true;
				break;
			}
		}
		Assert.assertTrue("Drug Name value verification", f);

	}
}
