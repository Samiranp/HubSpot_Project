package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import Utility.TimeUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static  WebDriver driver;
	public static Properties prop;

	/**
	 * This method is used to initialize the driver on the basis on given browser
	 * @param browser
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browser = prop.getProperty("browser");
		System.out.println("browser name is : " + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else {
			System.out.println(browser + " is not found, please pass the correct browserName");
		}

		driver.get(prop.getProperty("url"));
	     TimeUtil.MediumWait();
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();

		return driver;
	}

	/**
	 * this method is used to init/load the properties from config file
	 * @return prop
	 */
	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/Config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	public String getScreenshot() {
	File src =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") +"/Screenshots/"+System.currentTimeMillis();
	File destination = new File(path);
	
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return path;
	
	}
}