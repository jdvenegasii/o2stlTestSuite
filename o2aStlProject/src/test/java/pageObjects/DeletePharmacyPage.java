package pageObjects;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DeletePharmacyPage {

    public WebDriver ldriver;
    WaitHelper waitHelper;

    public DeletePharmacyPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
        waitHelper = new WaitHelper(ldriver);
    }
    
    @FindBy(how = How.XPATH, using = "/html//div[@id='root']/div[@class='App']//table/tbody/tr[4]/td[8]/button[@type='button']")
    @CacheLookup
    WebElement btnDeletePharmacy;
    
    @FindBy(how = How.XPATH, using = "//div[@id='root']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//div[@id='root']//table//th")
	List<WebElement> tableColumns;

    public void deletePharmacyWithNpi(String npi) {
    	waitHelper.WaitForElement(table, 30);
        //Step 1: Find the column index
        System.out.println("Number of Table Columns: " + tableColumns.size());

        int columnIndex = 1;
        for (int i=1; i<tableColumns.size(); i++) {
            System.out.println(tableColumns.get(i).getText());
            if ("NPI".equalsIgnoreCase(tableColumns.get(i).getText())){
                columnIndex = i +1;
                break;
            }
        }
        System.out.println("Column Index : " + columnIndex);

        //Step 2: verify "654321" value for NPI is in the table
        System.out.println("Number of rows : " + tableRows.size());

        boolean f = false;

        for (int i=1; i<=tableRows.size(); i++){
           String value = ldriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/table/tbody/tr["+i+"]/td["+columnIndex+"]")).getText();
           System.out.println(value);
           if (npi.equalsIgnoreCase(value)){
               f= true;
               ldriver.findElement(By.cssSelector("#root > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(8) > button")).click();
               break;
           }
        }
        Assert.assertTrue("NPI value verification", f);


    }

    public void searchListForDeletedPharmacyNPI(String npi) {
    	waitHelper.WaitForElement(table, 30);
		// Step 1: Find the column index
		System.out.println("Number of columns: " + tableColumns.size());

		int columnIndex = 1;
		for (int i = 1; i < tableColumns.size(); i++) {
			System.out.println(tableColumns.get(i).getText());
			if ("NPI".equalsIgnoreCase(tableColumns.get(i).getText())) {
				columnIndex = i + 1;
				break;
			}
		}
		System.out.println("Column Index : " + columnIndex);

		// Step 2: verify "Drug Name" value for drugname is not on the table
		System.out.println("Number of rows : " + tableRows.size());

		boolean t = true;

		for (int i = 1; i < tableRows.size(); i++) {
			String value = ldriver
					.findElement(
							By.xpath("//*[@id=\"root\"]/div/div/table/tbody/tr[" + i + "]/td[" + columnIndex + "]"))
					.getText();
			System.out.println(value);
			if (npi.equalsIgnoreCase(value)) {
				t = false;
				break;
			}
		}
		Assert.assertTrue("Pharmacy removal verification", t);

    }
    
    public void clickOnDeleteButton() { 
    	btnDeletePharmacy.click(); 
    }

}



