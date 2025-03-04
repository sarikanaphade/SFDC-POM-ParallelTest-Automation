package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;

@Listeners(TestListners.class)
public class HomeTest extends BaseTest{
	//WebDriver driver;
	public HomePage hp;
	
	@BeforeMethod
	public void setUp() {
//		WebDriver driver = BaseTest.getDriver();
//		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
//		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
//		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
//		WaitUtils.waitForElement(driver, ulp.profileDropdown);
//		hp = ulp.openHomePage(driver);
//		hp.verifyHomePageTitle(driver);
	}
	
	@Test(enabled=true)
	public void TC33_verifyLoggedInUser() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		hp = ulp.openHomePage(driver);
		hp.verifyHomePageTitle(driver);
		assertTrue(hp.verifyHomePageDetails(driver),"Home page details not displayed correctly");
	}
	
	@Test(enabled=true)
	public void TC34_verifyEditUser() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		hp = ulp.openHomePage(driver);
		hp.verifyHomePageTitle(driver);
		assertTrue(hp.verifyEditLastName(driver),"Home page details not displayed correctly");
	}
	
	@Test(enabled=true)
	public void TC35_verifyTabCustomization() {
		//when clicked on + icon, Internal server error page displaying so could not automate the test
	}
	
	@Test(enabled=true)
	public void TC36_verifyCalendarEvent() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		hp = ulp.openHomePage(driver);
		hp.verifyHomePageTitle(driver);
		assertTrue(hp.verifyCurrentDate(),"Current date not displayed correctly");
		assertTrue(hp.verifyCalendarEvent(driver),"Error while creating calendar event");
		assertTrue(hp.verifySubjectCombo(driver),"Error while selecting combobox values");
		assertTrue(hp.verifyEndTime(driver),"Error while selecting end time");
		assertTrue(hp.clickSaveEvent(driver),"Error while saving event");
		assertTrue(hp.verifyEventDetails(driver),"Details not displyed correctly");
	}
	
	@Test(enabled=true)
	public void TC37_verifyCalendarRecurringEvent() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		hp = ulp.openHomePage(driver);
		hp.verifyHomePageTitle(driver);
		assertTrue(hp.verifyCurrentDate(),"Current date not displayed correctly");
		assertTrue(hp.verifyRecurringOptions(driver),"Correct recurring options not displayed");
	}
	
//	@AfterMethod
//	public void tearDown() {
//		quitBrowser();
//	}
	
}
