package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

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

public class BaseClass {
	// General Pages
	public WebDriver driver;
	public static Logger logger;
	Properties configProp;
	// User Pages
	public LoginPage lp;
	public AddUserPage addCust;
	public SearchUserPage searchCust;
	// Pharmacy Pages
	public AddPharmacyPage addPharm;
	public DeletePharmacyPage deletePharm;
	public SearchPharmacyPage searchPharmacy;
	public ResetPharmacyListPage resetPharmacyList;
	public EditPharmacyPage editPharmacy;
	// Drug Pages
	public AddDrugPage addDrug;
	public DeleteDrugPage deleteDrug;
	public EditDrugPage editDrug;
	public SearchDrugPage searchDrug;
	public ResetDrugListPage resetDrugList;

	// created for generating random string for email
	public static String randomstring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
}
