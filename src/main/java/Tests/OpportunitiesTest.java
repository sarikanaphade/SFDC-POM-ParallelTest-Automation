package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.LoginPage;
import Pages.OpportunitiesPage;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;

@Listeners(TestListners.class)
public class OpportunitiesTest extends BaseTest{

	public OpportunitiesPage op;
	String username;
	String decryptPassword;
	@BeforeMethod
	public void setup() {
//		driver = BaseTest.getDriver(FileUtils.readLoginPropertiesFile("browser"), false);
//		initialization();
//		LoginPage lp = new LoginPage(driver);
		username=FileUtils.readLoginPropertiesFile("valid.username");
		decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
//		UserLandingPage ulp=lp.login(driver, username, decryptPassword);
//		WaitUtils.waitForElement(driver, ulp.profileDropdown);
//		op=ulp.openOpportunityPage(driver);
//		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
	}
	
	@Test(enabled=true)
	public void TC15_verifyOpportunityDropdown() {
		WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
    	LoginPage lp = new LoginPage(driver);
    	UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		op=ulp.openOpportunityPage(driver);
		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
		assertTrue(op.verifyOppDropdown(driver),"Opportinities Options are not available");
	}
	
	@Test(enabled=true)
	public void TC16_verifyCreateNewOpportunity() {
		WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
    	LoginPage lp = new LoginPage(driver);
    	UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		op=ulp.openOpportunityPage(driver);
		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
		op.verifyEditPage(driver);
		assertTrue(op.verifyCreateNewOpportunity(driver),"Error while creating new opportunity");
	}
	
	@Test(enabled=true)
	public void TC17_verifyOpportunityPipelineReport() {
		WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
    	LoginPage lp = new LoginPage(driver);
    	UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		op=ulp.openOpportunityPage(driver);
		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
		assertTrue(op.verifyOppPipelineReport(driver),"Opportunity pipeline report not displayed");
	}
	
	@Test(enabled=true)
	public void TC18_verifyStuckOpportunityReport() {
		WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
    	LoginPage lp = new LoginPage(driver);
    	UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		op=ulp.openOpportunityPage(driver);
		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
		assertTrue(op.verifyStuckOppReport(driver),"Stuck Opportunity report not displayed");
	}
	
	@Test(enabled=true)
	public void TC19_verifyQuarterlySummaryReport() {
		WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
    	LoginPage lp = new LoginPage(driver);
    	UserLandingPage ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		op=ulp.openOpportunityPage(driver);
		WaitUtils.waitForTitle(driver, FileUtils.readOppotunitiesPropertiesFile("opp.title"));
		assertTrue(op.verifySummaryReport(driver),"Quarterly summary report not displayed");
	}
	
//	@AfterMethod
//	public void tearDown() {
//		quitBrowser();
//	}
}
