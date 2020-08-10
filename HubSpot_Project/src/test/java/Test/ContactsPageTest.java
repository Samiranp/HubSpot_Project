package Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BasePage;
import Pages.ContactsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utility.Constants;
import Utility.ExcelUtil;

public class ContactsPageTest {
	Properties prop;
	WebDriver driver;

	BasePage basePage;
	LoginPage loginPage;
    HomePage HomePage;
    ContactsPage contactsPage;
    
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		HomePage =  loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		contactsPage = HomePage.goToContactsPage();
	
	}
	
	@Test(priority=1)
	public void VerifyContactsPagetitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contact page title is" + title);
		Assert.assertEquals(title, Constants.CONTACT_PAGE_TITLE);
			}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority =2 , dataProvider = "getContactsTestData")
	
	public void CreateNewContact(String email, String firstname, String lastname, String jobtitle){
		String name = 	contactsPage.createNewContact(email,firstname,lastname,jobtitle);
		Assert.assertEquals(name, firstname +" "+lastname);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}	
}
