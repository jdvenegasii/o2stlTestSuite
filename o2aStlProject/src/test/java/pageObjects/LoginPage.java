package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

public class LoginPage {

    public WebDriver ldriver;
    WaitHelper waitHelper;

    public LoginPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
        waitHelper = new WaitHelper(ldriver);
    }

    @FindBy(id="username")
    @CacheLookup
    WebElement txtUsername;

    @FindBy(id="password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath="//*[@id=\"root\"]/div/div/form/button[1]")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(linkText="Logout")
    @CacheLookup
    WebElement lnkLogout;

    public void setUserName(String uname)
    {
        txtUsername.clear();
        txtUsername.sendKeys(uname);
    }

    public void setPassword(String pwd)
    {
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin()
    {
        btnLogin.click();
    }

    public void clickLogout()
    {
        lnkLogout.click();
    }
}
