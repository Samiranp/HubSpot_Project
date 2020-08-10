package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Base.BasePage;
import Utility.Constants;
import Utility.ElementUtil;
import Utility.JavaScriptUtil;

public class ContactsPage extends BasePage {

	
	WebDriver driver;
	ElementUtil elementutil;
	 JavaScriptUtil jsutil ;
	By CreateContact = By.xpath("(//span[Text()='Create contact'])[1]");
	By CreateContactform = By.xpath("(//span[Text()='Create contact'])[2]");
	By email =By.xpath("//input[@data-field= 'email']");
	By firstName =By.xpath("//input[@data-field= 'firstname']");
	By lastName =By.xpath("//input[@data-field= 'lastname']");
	By jobtitle =By.xpath("//input[@data-field= 'jobtitle']");
	By contactsNavigationLink =By.xpath("(//i8n-String[text()='Contacts'])[2]");
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		 jsutil = new JavaScriptUtil(driver);
	}
	
	
	public String getContactsPageTitle() {
		
		return elementutil.waitForTitleToBePresent(Constants.CONTACT_PAGE_TITLE, 10);
	}
	
	public String createNewContact(String emailID, String firstname,String lastname, String jobTitle) {
		
		elementutil.waitForElementToBePresent(CreateContact, 10);
		elementutil.doClick(CreateContact);
		
		elementutil.waitForElementToBePresent(email, 5).sendKeys(emailID);
		elementutil.waitForElementToBePresent(firstName, 10).sendKeys(firstname);
		elementutil.waitForElementToBePresent(lastName, 5).sendKeys(lastname);
		elementutil.waitForElementToBePresent(jobtitle, 5).sendKeys(jobTitle);
		elementutil.waitForElementToBeClickable(CreateContactform, 10).click();  
		jsutil.clickElementByJS(elementutil.getElement(CreateContactform));
		elementutil.waitForElementToBePresent(contactsNavigationLink, 10);
		elementutil.doClick(contactsNavigationLink);
		
		String fullName = firstname +" "+lastname;
		String nameXpath = "(//span[text()='"+fullName+"'])[2]";
		elementutil.waitForElementToBePresent(contactsNavigationLink, 10);
		String contactName = elementutil.doGetText(By.xpath(nameXpath)).trim();
		elementutil.doClick(contactsNavigationLink);
		
		return contactName;
	}
	
	
	
	
	
	
	
}
 