package com.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SeleniumGoogleSearchTest {

	public static WebDriver driver;
	public static String appURL = "http://www.google.com";
	
	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws MalformedURLException {
		System.out.println("*******************");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		//caps.setCapability("platform", "Windows 7");
		//caps.setCapability("version", "38.0");
		driver = new RemoteWebDriver(new URL("http://naresh0407:3467d2cc-f3d3-4b6e-9028-4fa788ea630f@ondemand.saucelabs.com:80/wd/hub"), caps);

		driver.manage().window().maximize();
	}
	
	@Test
	public void testSauceLab() throws Exception{
		System.out.println("Opening Google..");
		driver.get("https://saucelabs.com/test/guinea-pig");
		System.out.println("title of page is: " + driver.getTitle());
		this.takeSnapShot(driver, "SauceTest.png") ;
	}
	
	@Test
	public void testSearchGoogle() throws Exception{
		System.out.println("Opening Google..");
		driver.navigate().to(appURL);
		WebElement java = driver.findElement(By.name("q"));
		java.sendKeys("Selenium");
		java.submit();
		System.out.println("Title of the page now is: " + driver.getTitle());
		this.takeSnapShot(driver, "Gsearch.png") ;
	}

	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
