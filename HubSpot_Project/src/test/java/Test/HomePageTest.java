package Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.BasePage;
import Pages.HomePage;
import Pages.LoginPage;
import Utility.Constants;

public class HomePageTest {

	
	Properties prop;
	WebDriver driver;

	BasePage basePage;
	LoginPage loginPage;
    HomePage HomePage;
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		HomePage =  loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	
	@Test
	
	public void VerifyHomePagetitleTest() {
		String title = HomePage.getHomePageTitle();
		System.out.println("Home page Title is" + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	@Test
	
	public void VerifyHomepageHeader() {
		
		String Header = HomePage.getHomePageHeader();
		System.out.println("Home page header is " + Header);
		Assert.assertEquals(Header,Constants.HOME_PAGE_Header );
	}

@AfterTest
public void tearDown() {
	driver.quit();
}	
	
}
