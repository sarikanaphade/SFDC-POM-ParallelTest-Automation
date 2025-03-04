package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.LeadsPage;
import Pages.LoginPage;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;

@Listeners(TestListners.class)
public class LeadsTest extends BaseTest{
	WebDriver driver;
	public LeadsPage ldp;
	public UserLandingPage ulp;
	String username;
	String decryptPassword;
	
	@BeforeMethod
	public void setup() {
//		WebDriver driver = BaseTest.getDriver();
//		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
//		LoginPage lp = new LoginPage(driver);
		username=FileUtils.readLoginPropertiesFile("valid.username");
		decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
//		ulp=lp.login(driver, username, decryptPassword);
//		WaitUtils.waitForElement(driver, ulp.profileDropdown);
//		ldp = ulp.openLeadsPage(driver);
	}
	
	@Test(enabled=true)
	public void TC20_verifyLeadsTab() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		ldp.verifyLeadPageTitle(driver);
	}
	
	@Test(enabled=true)
	public void TC21_verifyLeadsSelectView() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		assertTrue(ldp.verifyLeadsViewDropdown(),"Leads Dropdown not displayed");
	}
	
	@Test(enabled=true)
	public void TC22_verifyDefaultLeadsView() throws InterruptedException{
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		WaitUtils.waitForElement(driver, ldp.newLeadBtn);
		ldp.verifyLeadPageTitle(driver);
		assertTrue(ldp.selectDefaultView(),"Leads default view not selected");
		LoginPage loginPage=ulp.logout(driver);
    	WaitUtils.waitForElement(driver, loginPage.password);
		ulp=loginPage.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		assertTrue(ldp.verifyDefaultView(driver),"Default view not displayed correctly");
	}
	
	@Test(enabled=true)
	public void TC23_verifyTodaysLeads() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		ldp.verifyLeadPageTitle(driver);
		assertTrue(ldp.verifyTodaysLeadDropdown(driver),"Todays Leads are not displyed");
	}
	
	@Test(enabled=true)
	public void TC24_verifyCreateNewLeads() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		ldp = ulp.openLeadsPage(driver);
		ldp.verifyLeadPageTitle(driver);
		assertTrue(ldp.verifyCreateLead(driver),"Todays Leads are not displyed");
	}
	
//	@AfterMethod
//	public void tearDown() {
//		quitBrowser();
//	}

}
