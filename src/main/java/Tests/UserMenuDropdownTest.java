package Tests;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BasePage;
import BaseClass.BaseTest;
import Listners.TestListners;
import Pages.UserLandingPage;
import Utils.FileUtils;
import Utils.WaitUtils;
import Pages.LoginPage;
import Pages.MyProfilePage;
import Pages.MySettingsPage;

@Listeners(TestListners.class)
public class UserMenuDropdownTest extends BaseTest	{
	WebDriver driver;
    public LoginPage loginPage;

//    @BeforeMethod
//    public void setUp() {
//    	WebDriver driver = BaseTest.getDriver();
//		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
//        loginPage = new LoginPage(driver);  
//    }
    
    @Test(enabled=true)
    public void TC05_SelectUserMenu() {
    	WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
        loginPage = new LoginPage(driver);  
    	String decryptPassword = decryptPassword(getPropertyValue("password"));
    	UserLandingPage userLandingPage = loginPage.login(driver, getPropertyValue("username"), decryptPassword);
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        String expectedUsername = getPropertyValue("profilename");
        String actualUsername = userLandingPage.profileDropdown.getText();
        System.out.println(actualUsername);
        if(actualUsername.equals(expectedUsername)) {
        	System.out.println("TC05 - step 1 passsed");
        }else {
        	System.out.println("TC05 - step 1 failed");
        }
        userLandingPage.openProfileDropdown();
        String[] menul = {"My Profile" ,"My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		for(int i=0;i<userLandingPage.menuList.size();i++) {
			String menuname = userLandingPage.menuList.get(i).getText();
			if(!menul[i].equals(menuname)) {
				System.out.println("TC05 - FAILED "+ menul[i] + " not found in the user menu dropdown.");
			}
		}
		System.out.println("TC05 - PASSED");
    }
    
    
    @Test(enabled=true)
    public void TC06_MyProfile() throws InterruptedException {
    	WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
        loginPage = new LoginPage(driver);  
    	String decryptPassword = decryptPassword(getPropertyValue("password"));
    	UserLandingPage userLandingPage =  loginPage.login(driver,getPropertyValue("username"), decryptPassword);
        WaitUtils.waitForTitle(driver,"Home");
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        MyProfilePage myProfilePage = userLandingPage.clickMyProfile(driver);
        WaitUtils.waitForTitle(driver,"User: "+getPropertyValue("profilename"));
        myProfilePage.clickContactsEdit();
        myProfilePage.switchToEditProfileFrame(driver);
        if(myProfilePage.aboutTab.isDisplayed() && myProfilePage.contactTab.isDisplayed()) {
        	System.out.println("TC06 - Step 2 - passed");
        }else {
        	System.out.println("TC06 - Step 2 - failed");
        }
        myProfilePage.clickAboutTab();
        myProfilePage.editLastname("Test1");
        myProfilePage.clickSaveAll();
        BasePage.switchToDefault(driver);
        WaitUtils.waitForTitle(driver,"User: Sarika Test1");
        myProfilePage.clickPostLink();
        WaitUtils.waitForElement(driver,myProfilePage.postShareFrame);
        String posttext = "Test Post!!!";
        myProfilePage.EnterPostText(driver,posttext);
        BasePage.switchToDefault(driver);
        myProfilePage.clickShare();
        System.out.println(myProfilePage.sharedDisplayText.getText());
        if(myProfilePage.sharedDisplayText.getText().equals(posttext)) {
        	System.out.println("TC06 - Step 5 - passed");
        }else {
        	System.out.println("TC06 - Step 5 - failed");
        }
        myProfilePage = userLandingPage.clickMyProfile(driver);
        myProfilePage.clickFileLink();
        WaitUtils.waitForElement(driver,myProfilePage.uploadFile);
        String filePath = System.getProperty("user.dir")+"/src/main/java/InputData/TestFile.txt";
        myProfilePage.uploadFile(filePath);
        myProfilePage.clickShare();
        WaitUtils.waitForElement(driver,myProfilePage.sharedFileTitle);
        if(myProfilePage.sharedFileTitle.getText().equals("TestFile")) {
        	System.out.println("TC06 - Step 6 - passed");
        }else {
        	System.out.println("TC06 - Step 6 - failed");
        }
        myProfilePage.goToProfileImage(driver);
        WaitUtils.waitForElement(driver, myProfilePage.addPhotoLink);
        myProfilePage.clickAddPhoto();
        WaitUtils.waitForElement(driver, myProfilePage.uploadProfilePhotoFrame);
        String photoPath = System.getProperty("user.dir")+"/src/main/java/InputData/sampleFile.jpeg";
        myProfilePage.uploadProfilePhoto(driver,photoPath);
    }
    
    @Test(enabled=true)
    public void TC07_MySettings() {
    	WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
        loginPage = new LoginPage(driver);  
    	String decryptPassword = decryptPassword(getPropertyValue("password"));
    	UserLandingPage userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        MySettingsPage mySettingsPage = userLandingPage.clickMySettings(driver);
        String expectedMySettingsText = "Hello, "+getPropertyValue("profilename")+"!";
        String ActualMySettingsText = mySettingsPage.myProfileText.getText();
        Assert.assertEquals(ActualMySettingsText, expectedMySettingsText, "My Settings page not displayed!!");
        mySettingsPage.clickPersonalLink();
        mySettingsPage.clickLoginHistoryLink();
        mySettingsPage.clickDownloadLink();
        //verify csv file downloded?
        mySettingsPage.clickDisplayAndLogout();
        mySettingsPage.clickCustomizeTabs();
        mySettingsPage.selectCustomAppDropdown("Salesforce Chatter");
        mySettingsPage.selectReport("Reports");
        mySettingsPage.clickAddArrow();
        mySettingsPage.clickSaveReportTab();
        Assert.assertTrue(userLandingPage.reportsTab.isDisplayed(), "Reports tab not added in Content.");
        userLandingPage.clickSalesforceChatter();
        Assert.assertTrue(userLandingPage.reportsTab.isDisplayed(), "Reports tab not added in Salesforce Chatter.");
        userLandingPage.clickSales();
        Assert.assertTrue(userLandingPage.reportsTab.isDisplayed(), "Reports tab not added in Sales.");
        userLandingPage.clickMarketing();
        Assert.assertTrue(userLandingPage.reportsTab.isDisplayed(), "Reports tab not added in Marketing.");
        userLandingPage.clickContent();
		mySettingsPage = userLandingPage.clickMySettings(driver);
	 	mySettingsPage.clickDisplayAndLogout();
        mySettingsPage.clickCustomizeTabs();
        mySettingsPage.selectCustomAppDropdown("Salesforce Chatter");
        mySettingsPage.deSelectReport("Reports");
        mySettingsPage.clickRemoveArrow();
        mySettingsPage.clickSaveReportTab();
        mySettingsPage = userLandingPage.clickMySettings(driver);
        mySettingsPage.clickemailLink();
        mySettingsPage.clickmyEmailSettings();
        mySettingsPage.enterEmailName(getPropertyValue("emailName"));
        mySettingsPage.enterEmailAddress(getPropertyValue("emailAdress"));
        mySettingsPage.clickAutoBCCYes();
        mySettingsPage.saveEmailSettings();
        String expectedEmailMsg= "Your settings have been successfully saved.";
        String actualEmailMsg = mySettingsPage.emailMsg.getText();
        Assert.assertEquals(actualEmailMsg, expectedEmailMsg,"Error while saving email settings.");
        mySettingsPage.clickCalendarAndReminders();
        mySettingsPage.clickActivityReminders();
        
        mySettingsPage.clickOpenTestReminderBtn();
        String parentWindow = BasePage.getParentWindow(driver);
        Set<String> windowHandles = BasePage.getAllWindowHandles(driver);
		for(String handle:windowHandles) {
			if(handle!=parentWindow) {
				BasePage.switchToWindow(driver,handle);
				String ActualUrl = driver.getCurrentUrl();
				String ExpectedUrl = "https://testing-b-dev-ed.develop.my.salesforce.com/ui/core/activity/ReminderSettingsPage?setupid=Reminders&retURL=%2Fui%2Fsetup%2FSetup%3Fsetupid%3DCalendarAndReminders";
				Assert.assertEquals(ActualUrl, ExpectedUrl,"Test Reminder Window not opened.");
				driver.close();
			}
		}
		
        
		System.out.println("TC07 - PASSED");
    }
    
    @Test(enabled=true)
    public void TC08_DeveloperConsole() throws InterruptedException {
    	WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
        loginPage = new LoginPage(driver);  
    	String decryptPassword = decryptPassword(getPropertyValue("password"));
    	UserLandingPage userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        String parentWindow = BasePage.getParentWindow(driver);
        userLandingPage.clickDeveloperConsole();
        Set<String> windowHandles = BasePage.getAllWindowHandles(driver);
        System.out.println(windowHandles.size());
		for(String handle:windowHandles) {
			if(!handle.equals(parentWindow)) {
				BasePage.switchToWindow(driver,handle);
				String ActualTitle = driver.getTitle();
				String ExpectedTitle = "Developer Console";
				Assert.assertEquals(ActualTitle, ExpectedTitle,"Developer console Window not opened.");
				driver.close();
			}
		}
		BasePage.switchToWindow(driver,parentWindow);
		userLandingPage.logout(driver);
    }
    
    @Test(enabled=true)
    public void TC09_Logout() throws InterruptedException {
    	WebDriver driver = BaseTest.getDriver();
		driver.get(FileUtils.readLoginPropertiesFile("test.url"));
        loginPage = new LoginPage(driver);  
    	String decryptPassword = decryptPassword(getPropertyValue("password"));
    	UserLandingPage userLandingPage = loginPage.login(driver,getPropertyValue("username"), decryptPassword);
        WaitUtils.waitForElement(driver, userLandingPage.profileDropdown);
        loginPage = userLandingPage.logout(driver);
        WaitUtils.waitForElement(driver, loginPage.username);
        String ExpectedTitle = "Login | Salesforce";
        String ActualTitle = loginPage.getLoginPageTitle(driver);
        Assert.assertEquals(ActualTitle, ExpectedTitle, "Error while logging out.");
        		
    }
//    @AfterMethod
//    public void tearDown() {
//    	quitBrowser();
//    }
    
}
