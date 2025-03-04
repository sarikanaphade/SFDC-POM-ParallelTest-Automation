package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import BaseClass.BasePage;

public class UserLandingPage extends BasePage{
	
	public UserLandingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@id='userNavLabel']")
	public WebElement profileDropdown;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	public WebElement logout;
	
	@FindBy(id="userNavLabel")
	public WebElement userName;
	
	@FindBy(xpath="//div[@id='userNav-menuItems']/a")
	public List<WebElement> menuList;
	
	@FindBy(xpath="//a[contains(text(),'My Profile')]")
	public WebElement myProfile;
	
	@FindBy(xpath="//a[contains(text(),'My Settings')]")
	public WebElement mySettings;
	
	@FindBy(xpath="//a[contains(text(),'Developer Console')]")
	public WebElement devConsole;
	
	@FindBy(id="report_Tab")
	public WebElement reportsTab;
	
	@FindBy(id="Account_Tab")
	public WebElement accountTab;
	
	@FindBy(id="Opportunity_Tab")
	public WebElement oppurtunityTab;
	
	@FindBy(id="Lead_Tab")
	public WebElement leadsTab;
	
	@FindBy(id="Contact_Tab")
	public WebElement contactsTab;
	
	@FindBy(id="home_Tab")
	public WebElement homeTab;
	
	@FindBy(id="AllTab_Tab")
	public WebElement addTab;
	
	@FindBy(xpath="//span[@id='tsidLabel']")
	public WebElement contentDropdown;

	@FindBy(xpath="//a[normalize-space()='Salesforce Chatter']")
	public WebElement salesforceChatter;
	
	@FindBy(xpath="//a[normalize-space()='Marketing CRM Classic']")
	public WebElement marketing;
	
	@FindBy(xpath="//a[normalize-space()='Content']")
	public WebElement content;
	
	@FindBy(xpath="//a[normalize-space()='Sales']")
	public WebElement sales;
	
	

	public void openContentDropdown() {
		if(contentDropdown.isDisplayed()) {
			click(contentDropdown);
		}else {
			System.out.println("Content dropdown is not visible.");
		}
	}
	
	public AccountsPage openAccountsPage(WebDriver driver) {
		click(accountTab);
		return new AccountsPage(driver);
	}
	
	public OpportunitiesPage openOpportunityPage(WebDriver driver) {
		click(oppurtunityTab);
		return new OpportunitiesPage(driver);
	}
	
	public LeadsPage openLeadsPage(WebDriver driver) {
		click(leadsTab);
		return new LeadsPage(driver);
	}
	
	public ContactsPage openContactsPage(WebDriver driver) {
		click(contactsTab);
		return new ContactsPage(driver);
	}
	
	public HomePage openHomePage(WebDriver driver) {
		click(homeTab);
		return new HomePage(driver);
	}
	
	public void clickSalesforceChatter() {
		openContentDropdown();
		click(salesforceChatter);
	}
	
	public void clickSales() {
		openContentDropdown();
		click(sales);
	}
	
	public void clickMarketing() {
		openContentDropdown();
		click(marketing);
	}
	
	public void clickContent() {
		openContentDropdown();
		click(content);
	}
	
	public void openProfileDropdown() {
		if(profileDropdown.isDisplayed()) {
			click(profileDropdown);
		}else {
			System.out.println("Profile dropdown is not visible.");
		}
	}
	
	public LoginPage logout(WebDriver driver) {
		openProfileDropdown();
		click(logout);
		return new LoginPage(driver);
	}
	
	public MyProfilePage clickMyProfile(WebDriver driver) {
		openProfileDropdown();
		click(myProfile);
		return new MyProfilePage(driver);
	}
	
	public MySettingsPage clickMySettings(WebDriver driver) {
		openProfileDropdown();
		click(mySettings);
		return new MySettingsPage(driver);
	}
	
	public void clickDeveloperConsole() {
		openProfileDropdown();
		click(devConsole);
	}
	
	
}
