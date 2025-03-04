package Tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BasePage;
import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.AccountsPage;
import Pages.LoginPage;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;


@Listeners(TestListners.class)
public class AccountsTest extends BaseTest{
	public LoginPage loginPage;
    public BasePage basePage;
    public UserLandingPage userLandingPage;
    String decryptPassword;
    
    @BeforeMethod
    public void setUp() {
//    	driver = BaseTest.getDriver(getPropertyValue("browser"), false);
//    	initialization();
//        loginPage = new LoginPage(driver);
        decryptPassword = decryptPassword(getPropertyValue("password"));
//        userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
//        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
    }
    
    @Test(enabled=true)
    public void TC10_CreateAccount() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
		userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
	    WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        AccountsPage accountsPage =userLandingPage.openAccountsPage(driver);
        assertTrue(accountsPage.verifyAccountPageTitle(driver),"Accounts page not opened!!");
        accountsPage.createNewAccountBtn();
        accountsPage.enterAccountName("VerifyUserAcc1");
        accountsPage.saveAccount();
        String actualAccTitle = accountsPage.getAccountsPageTitle(driver);
        String expectedAccTitle = "Account: VerifyUserAcc1";
        WaitUtils.waitForElement(driver, accountsPage.accDetails);
        Assert.assertTrue(actualAccTitle.contains(expectedAccTitle),"New account page not opened");
        Assert.assertTrue(accountsPage.accDetails.getText().contains("VerifyUserAcc1"),"Incorrect account name is dispalyed");
        accountsPage = userLandingPage.openAccountsPage(driver);
        accountsPage.createNewAccountBtn();
        accountsPage.enterAccountName("VerifyUserAcc2");
        accountsPage.saveAccount();
    }
    
    @Test(enabled=true)
    public void TC11_CreateView() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
		userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
	    WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        AccountsPage accountsPage =userLandingPage.openAccountsPage(driver);
        assertTrue(accountsPage.verifyAccountPageTitle(driver),"Accounts page not opened!!");
        accountsPage.createNewViewBtn();
        String viewName = "VerifyUserView2";
        accountsPage.enterViewName(viewName);
        accountsPage.enterUniqueName(viewName);
        accountsPage.saveAccount();
        for(WebElement options: accountsPage.getSelectViewOptions()) {
        	if(options.getText().contains(viewName)) {
        		System.out.println("View Name exists");
        	}
        }
        Assert.assertEquals(accountsPage.getSelectedView().getText(),viewName, "View is not displayed.");
    }
    
    @Test(enabled=true)
    public void TC12_EditView() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
		userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
	    WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        AccountsPage accountsPage = userLandingPage.openAccountsPage(driver);
        String viewName = "VerifyUserView2";
        accountsPage.selectView(viewName);
        accountsPage.clickEditView();
        Assert.assertEquals(accountsPage.editViewLabel.getText(),"Edit View","Edit view page not dispalyed");
        String newViewName = "VerifyUserNewView2";
        accountsPage.enterViewName(newViewName);
        accountsPage.selectField("Account Name");
        accountsPage.selectOperator("contains");
        accountsPage.enterValue("UserAcc");
        accountsPage.selectFieldToDisplay("Last Activity");
        accountsPage.addField();
        accountsPage.saveAccount();
        Assert.assertEquals(accountsPage.getSelectedView().getText(),newViewName, "View is not displayed.");
        Assert.assertTrue(accountsPage.lastActivityColumn.isDisplayed(),"Last Activity column not displayed");
        List<WebElement> rows = accountsPage.viewAccountsTable.findElements(By.tagName("tr"));
        for(int row = 0;row<rows.size();row++) {
        	List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));
        	int accCol =3;
        	String accname = cols.get(accCol).getText();
        	Assert.assertTrue(accname.contains("UserAcc"),"Correct data not gets filtered.");
        }
    }
    
    @Test(enabled=true)
    public void TC13_MergeAccounts() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
		userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
	    WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        AccountsPage accountsPage = userLandingPage.openAccountsPage(driver);
        accountsPage.clickMergeAccount();
        accountsPage.clickMergeAccInput("VerifyUserAcc");
        accountsPage.clickFindAccount();
        accountsPage.clickNextBtn();
        accountsPage.clickMergeBtn();
        Assert.assertTrue(BasePage.switchToAlert(driver).getText().contains("Proceed with the record merge?"),"Alert not displayed");
        BasePage.switchToAlert(driver).accept();
        String actualTitle = accountsPage.getAccountsPageTitle(driver);
        String expectedTitle = "Accounts";
        Assert.assertTrue(actualTitle.contains(expectedTitle),"Accounts page not opened");
        List<WebElement> rows = accountsPage.accList.findElements(By.tagName("tr"));
        for(int row = 1; row<rows.size();row++) {
        	WebElement col = rows.get(row).findElement(By.tagName("th"));
        	String accName = col.getText();
        	if(accName.equals("VerifyUserAcc2")) {
        		System.out.println("Account merged successfully");
        	}
        }
    }

    
    @Test(enabled=true)
    public void TC14_verifyCreateAccountReport(Method name) throws InterruptedException {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
		userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
	    WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
    	AccountsPage accountsPage = userLandingPage.openAccountsPage(driver);
        assertTrue(accountsPage.verifyAccountPageTitle(driver),"Accounts page not opened!!");
        assertTrue(accountsPage.verifyCreateAccountReport(driver),"Unsaved reports not displayed!!");
        assertTrue(accountsPage.verifyReportOptions(driver,name),"List of qualif ifed accounts nor dispalyed correctly!!");
    }
    
//    @AfterMethod
//    public void tearDown() {
//    	quitBrowser();
//    }
}
