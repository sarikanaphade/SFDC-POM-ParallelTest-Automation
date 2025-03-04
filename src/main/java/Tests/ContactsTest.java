package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.ContactsPage;
import Pages.LoginPage;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;

@Listeners(TestListners.class)
public class ContactsTest extends BaseTest{
	public ContactsPage cp;
	public UserLandingPage ulp;
	
	@BeforeMethod
	public void setUp() {
//		WebDriver driver = BaseTest.getDriver();
//		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
//		LoginPage lp = new LoginPage(driver);
//		String username=FileUtils.readLoginPropertiesFile("valid.username");
//		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
//		ulp=lp.login(driver, username, decryptPassword);
//		WaitUtils.waitForElement(driver, ulp.profileDropdown);
//		cp = ulp.openContactsPage(driver);
//		cp.verifyContactPageTitle(driver);
	}

	@Test(enabled=true)
	public void TC25_verifyCreateNewContact() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyCreateContact(driver),"Unable to create contact");
	}
	
	@Test(enabled=true)
	public void TC26_verifyCreateNewViewContact() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyCreateView(driver),"Unable to create new view");
	}
	
	@Test(enabled=true, dependsOnMethods="TC26_verifyCreateNewViewContact")
	public void TC27_verifyRecentlyCreatedContact() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyRecentContacts(driver,FileUtils.readContactsPropertiesFile("con.view.option")),"Recently creaated contacts not displayed");
	}
	
	@Test(enabled=true)
	public void TC28_verifyMyContactsView() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyMyContacts(driver),"My contacts not displayed");
	}
	
	@Test(enabled=true)
	public void TC29_verifyViewContact(){
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyViewContact(driver),"Unable to view contact");
	}
	
	@Test(enabled=true)
	public void TC30_verifyInvalidView(){
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyInvalidView(driver),"Not throwing error message");
	}
	
	@Test(enabled=true)
	public void TC31_verifyCancelView(){
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyCancelView(driver),"Unable to cancel view, view got created");
	}
	
	@Test(enabled=true)
	public void TC32_verifySaveAndNewContact() {
		WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		LoginPage lp = new LoginPage(driver);
		String username=FileUtils.readLoginPropertiesFile("valid.username");
		String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		ulp=lp.login(driver, username, decryptPassword);
		WaitUtils.waitForElement(driver, ulp.profileDropdown);
		cp = ulp.openContactsPage(driver);
		cp.verifyContactPageTitle(driver);
		assertTrue(cp.verifyCreateNewContact(driver),"Unable to save view using Save and New button");		
	}
	
//	@AfterMethod
//	public void tearDown() {
//		quitBrowser();
//	}

}
