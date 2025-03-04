package Pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import BaseClass.BasePage;

public class MySettingsPage extends BasePage{
	public MySettingsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='content']/h1")
	public WebElement myProfileText;
	
	@FindBy(xpath="//span[@id='PersonalInfo_font']")
	public WebElement personalLink;
	
	@FindBy(xpath="//span[text()='Login History']")
	public WebElement loginHistoryLink;
	
	@FindBy(xpath="//a[contains(text(),'Download login history')]")
	public WebElement downloadLink;
	
	@FindBy(xpath="//table[@class='list']/tbody/tr[2]/td")
	public WebElement longinHistoryTable;
	
	@FindBy(xpath="//span[@id='DisplayAndLayout_font']")
	public WebElement displayAndLogout;

	@FindBy(xpath="//span[@id='CustomizeTabs_font']")
	public WebElement customizeTabs;

	@FindBy(xpath="//select[@id='p4']")
	public WebElement customAppDropdown;
	
	@FindBy(xpath="//select[@id='duel_select_0']")
	public WebElement selectReport;
	
	@FindBy(xpath="//select[@id='duel_select_1']")
	public WebElement deSelectReport;
	
	@FindBy(xpath="//img[@title='Add']")
	public WebElement addReportArrow;
	
	@FindBy(xpath="//img[@title='Remove']")
	public WebElement removeReportArrow;
	
	@FindBy(xpath="//input[@title='Save']")
	public WebElement saveBtn;
	
	@FindBy(xpath="//span[@id='EmailSetup_font']")
	public WebElement emailLink;
	
	@FindBy(xpath="//a[@id='EmailSettings_font']")
	public WebElement myEmailSettings;
	
	@FindBy(xpath="//input[@id='sender_name']")
	public WebElement emailName;
	
	@FindBy(name="sender_email")
	public WebElement emailAddress;

	@FindBy(xpath="//input[@id='auto_bcc1']")
	public WebElement autoBcc;

	@FindBy(xpath="//input[@title='Save']")
	public WebElement saveButton;

	@FindBy(xpath="//span[@id='CalendarAndReminders_font']")
	public WebElement calendarAndReminders;
	
	@FindBy(xpath="//span[@id='Reminders_font']")
	public WebElement activityReminders;
	
	@FindBy(xpath="//input[@id='testbtn']")
	public WebElement openTestReminder;
	
	@FindBy(xpath="//div[@class='messageText']")
	public WebElement emailMsg;
	

	public void clickPersonalLink() {
		click(personalLink);
	}
	
	public void clickLoginHistoryLink() {
		click(loginHistoryLink);
	}
	
	public void clickDownloadLink() {
		click(downloadLink);
	}
	
	public void clickDisplayAndLogout() {
		click(displayAndLogout);
	}
	
	public void clickCustomizeTabs() {
		click(customizeTabs);
	}
	
	public void selectCustomAppDropdown(String option) {
		Select select = new Select(customAppDropdown);
        select.selectByVisibleText(option);
	}
	
	public void selectReport(String option) {
		Select select = new Select(selectReport);
        select.selectByVisibleText(option);
	}
	
	public void deSelectReport(String option) {
		Select select = new Select(deSelectReport);
        select.selectByVisibleText(option);
	}
	
	public void clickAddArrow() {
		click(addReportArrow);
	}
	
	public void clickRemoveArrow() {
		click(removeReportArrow);
	}
	
	public void clickSaveReportTab() {
		click(saveBtn);
	}
	
	public void clickemailLink() {
		click(emailLink);
	}
	
	public void clickmyEmailSettings() {
		click(myEmailSettings);
	}
	
	public void enterEmailName(String eName) {
		enterText(emailName,eName);
	}
	
	public void enterEmailAddress(String eAddress) {
		enterText(emailAddress,eAddress);
	}
	
	public void clickAutoBCCYes() {
		if(!autoBcc.isSelected()) {
			click(autoBcc);
		}
	}
	
	public void saveEmailSettings() {
		click(saveButton);
	}
	
	public void clickCalendarAndReminders() {
		click(calendarAndReminders);
	}
	
	public void clickActivityReminders() {
		click(activityReminders);
	}
	
	public void clickOpenTestReminderBtn() {
		click(openTestReminder);
		}
		
	public String getParentWindowHandle(WebDriver driver) {
		return getParentWindow(driver);
	}
	
	public static Set<String> getAllWindowHandles(WebDriver driver){
		return getAllWindowHandles(driver);
	}
	
	public static void switchToWindow(WebDriver driver,String handle) {
		switchToWindow(driver,handle);
	}
	
}
