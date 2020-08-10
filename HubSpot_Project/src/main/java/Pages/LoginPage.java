package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;
import Utility.Constants;
import Utility.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementutil;

	// 1. By locators:
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");

	// 2. constructor of the page class:

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}

	// 3. page actions/methods:

	public String getLoginPageTitle() {
		return elementutil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}

	public boolean checkSignUpLink() {
		return elementutil.doIsDisplayed(signUpLink);
	}

	public HomePage doLogin(String un, String pwd) {	
		elementutil.doSendKeys(username, un);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(loginButton);
		
		return new HomePage(driver);
	}

}