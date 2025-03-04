package Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import BaseClass.BaseTest;
import Pages.UserLandingPage;
import Utils.*;
import Pages.ForgotPasswordPage;
import Pages.LoginPage;
import Listners.TestListners;

@Listeners(TestListners.class)
public class LoginTest extends BaseTest	{
	
    public LoginPage loginPage;
  
//    @BeforeMethod
//    public void setUp() {
//    	  
//    }
    
    @Test(enabled=true)
    public void TC01_TestErrorMessage() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
        loginPage.enterUsername(FileUtils.readLoginPropertiesFile("valid.username"));
        loginPage.enterPassword("");
        loginPage.clickLogin();
        String actualErrorMsg = loginPage.errorMsg.getText();
        String expectedErrorMsg = FileUtils.readLoginPropertiesFile("error.pwd.text");
        assertTrue(actualErrorMsg.equals(expectedErrorMsg),"Error message not displayed.") ;
    }
    
    @Test(enabled=true)
    public void TC02_TestValidLogin() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
    	String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
    	UserLandingPage userLandingPage = loginPage.login(driver,FileUtils.readLoginPropertiesFile("valid.username"), decryptPassword);
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        assertTrue(driver.getTitle().contains("Home"),"Landing page is not diaplyed.");
    }
   
    @Test(enabled = true)
    public void TC03_TestRememberMeLogin(){
		ExtentTest test2 = threadLocalTest.get();
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
    	String decryptPassword = decryptPassword(FileUtils.readLoginPropertiesFile("valid.password"));
    	UserLandingPage userLandingPage = loginPage.rememberMeLogin(driver,FileUtils.readLoginPropertiesFile("valid.username"), decryptPassword);
    	WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
    	test2.info("Logged into the SFDC application");
    	loginPage = userLandingPage.logout(driver);
    	WaitUtils.waitForElement(driver, loginPage.password);
    	assertTrue(loginPage.rememberUserName.getDomAttribute("aria-label").equals(FileUtils.readLoginPropertiesFile("valid.username")),"Username not remembered.");
    	test2.info("Remeber Me functionality working correctly!");
    }
    
    @Test(enabled=true)
    public void TC04A_TestForgotPassword() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
    	ForgotPasswordPage forgotPasswordPage = loginPage.clickforgotPassword(driver);
        WaitUtils.waitForElement(driver, forgotPasswordPage.username);
        forgotPasswordPage.enterUsername(FileUtils.readLoginPropertiesFile("valid.username"));
        forgotPasswordPage.clickcontinueButton();
        WaitUtils.waitForElement(driver,forgotPasswordPage.emailMessage);
        String actualEmailMsg = forgotPasswordPage.emailMessage.getText();
        String expectedEmailMsg = FileUtils.readLoginPropertiesFile("email.msg");
        assertTrue(actualEmailMsg.equals(expectedEmailMsg),"Email not sent for restting password."); 
    }

    @Test(enabled=true)
    public void TC04B_TestErrorMessage() {
    	WebDriver driver = BaseTest.getDriver();
    	driver.get(FileUtils.readLoginPropertiesFile("test.url"));
		loginPage = new LoginPage(driver);
    	loginPage.enterUsername(FileUtils.readLoginPropertiesFile("invalid.username"));
        loginPage.enterPassword(FileUtils.readLoginPropertiesFile("invalid.password"));
        loginPage.clickLogin();
        String actualErrorMsg = loginPage.errorMsg.getText();
        String expectedErrorMsg = FileUtils.readLoginPropertiesFile("error.invalid.text");
        assertTrue(actualErrorMsg.equals(expectedErrorMsg),"Error for invalid credentials not diaplyed.");
    }
    
//    @AfterMethod
//    public void tearDown() {
//    	quitBrowser();
//    }
    
}
