package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;
import Pages.ContactsPage;
import Utility.Constants;
import Utility.ElementUtil;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementutil;
	By header = By.cssSelector("h1.dashboard-selector__title");
	By ContactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By ContactsLinkSecondary = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getHomePageTitle() {
		return elementutil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
	}

	public String getHomePageHeader() {
		if (elementutil.doIsDisplayed(header)) {
			return elementutil.doGetText(header);
		}
		return null;
	}
	
	public ContactsPage goToContactsPage() {
		clickonContacts();
		return new ContactsPage(driver);
	}
	
	private void clickonContacts() {
		
		elementutil.waitForElementToBePresent(ContactsLinkPrimary, 5);
		elementutil.doClick(ContactsLinkPrimary);
		elementutil.waitForElementToBePresent(ContactsLinkSecondary, 3);
		elementutil.doClick(ContactsLinkSecondary);
	}

}