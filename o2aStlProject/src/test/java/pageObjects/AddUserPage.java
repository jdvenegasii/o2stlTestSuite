package pageObjects;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.WaitHelper;

public class AddUserPage {

	public WebDriver ldriver;
	WaitHelper waitHelper;

	public AddUserPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
	}

	By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By getLnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	By getLnkSignUp_menuitem = By.cssSelector("#basic-navbar-nav > div > a.nav-link.ml-3.mr-3.register");

	By btnAddnew = By.xpath("//a[@class='btn bg-blue']"); // Add New

	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");

	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[]contains(text(),'Vendors')");

	By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");

	By txtFirstName = By.cssSelector("#first_name");
	By txtLastName = By.cssSelector("#last_name");

	By txtDob = By.cssSelector("#Dob");
	By txtAddress1 = By.cssSelector("#Address_1");
	By txtAddress2 = By.cssSelector("#Address_2");
	By txtCity = By.cssSelector("#City");
	By txtState = By.cssSelector("#State");
	By txtUsername = By.cssSelector("#username");
	By txtPw = By.cssSelector("#password");

	By txtCompanyName = By.xpath("//input[@id='Company']");

	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");

	By btnRegister = By.xpath("//*[@id=\"root\"]/div/div/form/button[1]");

	By btnSave = By.xpath("//button[@name='save']");

	// Actions Methods

	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(getLnkCustomers_menuitem).click();
	}

	public void clickOnAddNew() {
		ldriver.findElement(btnAddnew).click();
	}

	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}

	public void setCustomerRoles(String role) throws InterruptedException {
		if (!role.equals("Vendors")) // if role is vendors should not delete Register as per req.
		{
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}

		ldriver.findElement(txtcustomerRoles).click();

		WebElement listitem;

		Thread.sleep(3000);

		if (role.equals("Administrators")) {
			listitem = ldriver.findElement(lstitemAdministrators);
		} else if (role.equals("Guests")) {
			listitem = ldriver.findElement(lstitemGuests);
		} else if (role.equals("Registered")) {
			listitem = ldriver.findElement(lstitemRegistered);
		} else if (role.equals("Vendors")) {
			listitem = ldriver.findElement(lstitemVendors);
		} else {
			listitem = ldriver.findElement(lstitemGuests);
		}

		// listitem.click();
		// Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].click();", listitem);

	}

	public void setManagerOfVendor(String value) {
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}

	public void setGender(String gender) {
		if (gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		} else if (gender.equals("Female")) {
			ldriver.findElement(rdFeMaleGender).click();
		} else {
			ldriver.findElement(rdMaleGender).click(); // Default
		}
	}

	public String randomstring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	public void setFirstName() {
		ldriver.findElement(txtFirstName).sendKeys(randomstring());
	}

	public void setLastName() {
		ldriver.findElement(txtLastName).sendKeys(randomstring());
	}

	public void setDob(String dob) {
		ldriver.findElement(txtDob).sendKeys(dob);
	}

	public void setAddress1(String address1) {
		ldriver.findElement(txtAddress1).sendKeys(address1);
	}

	public void setAddress2(String address2) {
		ldriver.findElement(txtAddress2).sendKeys(address2);
	}

	public void setCity(String city) {
		ldriver.findElement(txtCity).sendKeys(city);
	}

	public void setState(String state) {
		ldriver.findElement(txtState).sendKeys(state);
	}

	public void setUsername() {
		ldriver.findElement(txtUsername).sendKeys(randomstring());
	}

	public void setPw(String pw) {
		ldriver.findElement(txtPw).sendKeys(pw);
	}

	public void setCompanyName(String comname) {
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}

	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}

	public void clickOnRegister() {
		ldriver.findElement(btnRegister).click();
	}

	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}

	public void clickOnSignUpMenuItem() {
		ldriver.findElement(getLnkSignUp_menuitem).click();
	}

}
