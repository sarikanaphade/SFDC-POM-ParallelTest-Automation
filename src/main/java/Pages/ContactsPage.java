package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import BaseClass.BasePage;
import Utils.CommonUtils;
import Utils.FileUtils;
import Utils.WaitUtils;

public class ContactsPage extends BasePage{

	public ContactsPage(WebDriver driver) {
		super(driver);
	}
	public UserLandingPage ulp;
	
	@FindBy(xpath="//input[@title='New']")
	public WebElement newBtn;
	
	@FindBy(xpath="//input[@id='name_lastcon2']")
	public WebElement lastName;
	
	@FindBy(xpath="//input[@id='con4']")
	public WebElement accName;
	
	@FindBy(xpath="(//input[@name='save'])[2]")
	public WebElement saveBtn;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	public WebElement contactHeader;
	
	@FindBy(xpath="//a[text()='Create New View']")
	public WebElement createNewView;
	
	@FindBy(xpath="//input[@id='fname']")
	public WebElement viewName;
	
	@FindBy(xpath="//input[@id='devname']")
	public WebElement uniqueViewName;
	
	@FindBy(xpath="//select[@name='fcf']")
	public WebElement selectViewDropdown;
	
	@FindBy(xpath="//select[@id='hotlist_mode']")
	public WebElement recentlyCreated;
	
	@FindBy(xpath="//table[@class='list']")
	public WebElement recentContactsList;
	 //contact name - 2nd tr-th-a
	
	@FindBy(xpath="//div[@class='x-grid3-row']")
	public WebElement myContactsList;
	
	@FindBy(xpath="//table[@class='x-grid3-row-table']")
	public List<WebElement> contactsTable;
	
	@FindBy(xpath="//span[contains(@id,'_paginator_rpp_target')]")
	public WebElement noOfRows;
	
	@FindBy(xpath="//div[@class='x-grid3-body']/div")
	public WebElement noRecords;
	
	@FindBy(xpath="//span[@class='prevNext'][3]")
	public WebElement next;
	
	@FindBy(xpath="//span[@class='waitingDescription']")
	public WebElement loading;
	
	@FindBy(xpath="//div[@class='requiredInput']/div[@class='errorMsg']")
	public WebElement errorMessage;
	
	@FindBy(xpath="(//input[@name='cancel'])[2]")
	public WebElement cancelBtn;
	
	@FindBy(xpath="(//input[@name='save_new'])[2]")
	public WebElement saveAndNewBtn;
	
	public void verifyContactPageTitle(WebDriver driver) {
		String expTitle = FileUtils.readContactsPropertiesFile("con.title");
		String actualTitle= getTitle(driver);
		assertEquals(expTitle,actualTitle,"Contacts Page not displayed");
	}
	
	public boolean verifyViewSelected(String viewName) {
		boolean isSelected = false;
		Select actualselect = new Select(selectViewDropdown);
		List<WebElement> selectedOption =  actualselect.getAllSelectedOptions();
		if(selectedOption.size()==1 ) {
			for(WebElement option: selectedOption) {
					if(option.getText().equals(viewName)) {
						isSelected = true;
					}
			}
		}
		return isSelected;
	}
	
	public boolean verifyCreateContact(WebDriver driver) {
		boolean isVerified = true;
		click(newBtn);
		String expTitle = FileUtils.readContactsPropertiesFile("con.edit.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Contact Edit Page not displayed");
		enterText(lastName,FileUtils.readContactsPropertiesFile("con.lastname"));
		enterText(accName,FileUtils.readContactsPropertiesFile("con.acc.name"));
		click(saveBtn);
		String expContactTitle = FileUtils.readContactsPropertiesFile("con.lastname");
		String actualContactTitle = BasePage.getTitle(driver);
		if(!actualContactTitle.contains(expContactTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		String conHeader = contactHeader.getText();
		if(!conHeader.equals(FileUtils.readContactsPropertiesFile("con.lastname"))) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
	public boolean verifyCreateView(WebDriver driver) {
		click(createNewView);
		String expTitle = FileUtils.readContactsPropertiesFile("con.view.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Contact New view Page not displayed");
		enterText(viewName,FileUtils.readContactsPropertiesFile("con.view.name"));
		enterText(uniqueViewName,FileUtils.readContactsPropertiesFile("con.view.uniquename"));
		click(saveBtn);
		String expHomeTitle = FileUtils.readContactsPropertiesFile("con.home.title");
		String actualHomeTitle= getTitle(driver);
		assertEquals(expHomeTitle,actualHomeTitle,"Contacts Home Page not displayed");
		boolean isSelected = verifyViewSelected(FileUtils.readLeadsPropertiesFile("con.view.name"));
		return isSelected;
	}
	
	public boolean verifyRecentContacts(WebDriver driver, String contactName) {
		Select recentCont = new Select(recentlyCreated);
		recentCont.selectByVisibleText(contactName);
		List<WebElement> recentList = recentContactsList.findElements(By.tagName("tr"));
		String conName= recentList.get(1).findElement(By.xpath("//th/a")).getText();
		String expConName=FileUtils.readContactsPropertiesFile("con.lastname");
		if(!conName.equals(expConName)) {
			System.out.println("Recently created account not displayed in the Recent Contacts list");
			CommonUtils.captureScreenshot(driver);
			return false;
		}
		return true;
	}
	
	public boolean verifyMyContacts(WebDriver driver) throws InterruptedException {
		Select viewOption = new Select(selectViewDropdown);
		viewOption.selectByVisibleText(FileUtils.readLeadsPropertiesFile("con.view.mycontacts"));
		verifyViewSelected(FileUtils.readLeadsPropertiesFile("con.view.mycontacts"));
		int status = 8;
		Thread.sleep(2000);
		if(!noRecords.getText().equals("No records to display.")) {
		int len = noOfRows.getText().length();
		System.out.println(noOfRows.getText());
		int noRows = Integer.parseInt(noOfRows.getText().substring(len-2,len).trim());
		int entries = Integer.parseInt(noOfRows.getText().split(" ")[0].split("-")[1]);
		while(noRows >= contactsTable.size()) {
			System.out.println(contactsTable.size());
		for(int r=0;r<contactsTable.size();r++) {
			WebElement row = contactsTable.get(r).findElement(By.tagName("tr"));
			List<WebElement> cols = row.findElements(By.tagName("td"));
			String contactOwer = cols.get(status).getText();
			System.out.println(contactOwer);
			if(!contactOwer.equals(FileUtils.readLeadsPropertiesFile("con.owner"))){
				return false;
			}
		}
		//click Next if enabled else break;
		if(!next.isEnabled()) {
			break;
		}else {
			click(next);
			//WaitUtils.waitForElement(driver, contactsTable);
			WaitUtils.waitForElementInvisibility(driver,loading);
		}
		noRows = noRows - entries;
		}
		}else {
			System.out.println("No My contacts available.");
		}
		return true;
	}
	
	public boolean verifyViewContact(WebDriver driver) {
		boolean isVerified=true;
		List<WebElement> recentList = recentContactsList.findElements(By.tagName("tr"));
		WebElement conName= recentList.get(1).findElement(By.xpath("//th/a"));
		click(conName);
		String expContactTitle = FileUtils.readContactsPropertiesFile("con.lastname");
		String actualContactTitle = BasePage.getTitle(driver);
		if(!actualContactTitle.contains(expContactTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		String conHeader = contactHeader.getText();
		if(!conHeader.equals(FileUtils.readContactsPropertiesFile("con.lastname"))) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
	public boolean verifyInvalidView(WebDriver driver) {
		click(createNewView);
		String expTitle = FileUtils.readContactsPropertiesFile("con.view.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Contact New view Page not displayed");
		enterText(uniqueViewName,FileUtils.readContactsPropertiesFile("con.view.invalid.uniquename"));
		click(saveBtn);
		if(!errorMessage.getText().equals(FileUtils.readContactsPropertiesFile("con.invalid.error"))) {
			CommonUtils.captureScreenshot(driver);
			return false;
		}
		return true;
	}
	
	public boolean verifyCancelView(WebDriver driver) {
		click(createNewView);
		String expTitle = FileUtils.readContactsPropertiesFile("con.view.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Contact New view Page not displayed");
		enterText(viewName,FileUtils.readContactsPropertiesFile("con.view.testname"));
		enterText(uniqueViewName,FileUtils.readContactsPropertiesFile("con.view.testuniquename"));
		click(cancelBtn);
		verifyContactPageTitle(driver);
		Select viewOption = new Select(selectViewDropdown);
		List<WebElement> options = viewOption.getOptions();
		for(WebElement option:options) {
			if(option.getText().equals(FileUtils.readContactsPropertiesFile("con.view.testname"))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyCreateNewContact(WebDriver driver) {
		click(newBtn);
		String expTitle = FileUtils.readContactsPropertiesFile("con.edit.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Contact Edit Page not displayed");
		enterText(lastName,FileUtils.readContactsPropertiesFile("con.test.lastname"));
		enterText(accName,FileUtils.readContactsPropertiesFile("con.test.uniquename"));
		click(saveAndNewBtn);
		String exTitle = FileUtils.readContactsPropertiesFile("con.edit.title");
		String ActulTitle = BasePage.getTitle(driver);
		assertTrue(!exTitle.contains(ActulTitle),"Contact Edit Page not displayed");
		CommonUtils.captureScreenshot(driver);
		UserLandingPage userLandingPage = new UserLandingPage(driver);
		userLandingPage.openContactsPage(driver);
		verifyContactPageTitle(driver);
		if(verifyRecentContacts(driver,FileUtils.readContactsPropertiesFile("con.test.lastname"))) {
			return true;
		}
		
		return false;
	}
	
	
}
