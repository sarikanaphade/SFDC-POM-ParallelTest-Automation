package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class LeadsPage extends BasePage{

	public LeadsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//select[@name='fcf']")
	public WebElement leadsDropdown;
	
	@FindBy(xpath="//input[@name='go']")
	public WebElement goBtn;
	
//	@FindBy(xpath="//div[@class='x-grid3-row']")
//	public WebElement openLeadsTable;
	
	//elemnt scrolldown - check scrooling
	
	@FindBy(xpath="//table[@class='x-grid3-row-table']")
	public List<WebElement> leadsTable;
	
//	@FindBy(xpath="//span[@id='00Baj00000R6x4P_paginator_rpp_target']")
//	public WebElement noOfRows;
	
	@FindBy(xpath="//span[contains(@id,'_paginator_rpp_target')]")
	public WebElement noOfRows;
	
	@FindBy(xpath="//div[@class='x-grid3-body']/div")
	public WebElement noRecords;
	
	@FindBy(xpath="//span[@class='prevNext'][3]")
	public WebElement next;
	
	@FindBy(xpath="//span[@class='waitingDescription']")
	public WebElement loading;
	
	@FindBy(name="new")
	public WebElement newLeadBtn;
	
	@FindBy(id="name_lastlea2")
	public WebElement lastName;
	
	@FindBy(id="lea3")
	public WebElement companyName;

	@FindBy(xpath="(//input[@title='Save'])[2]")
	public WebElement saveBtn;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	public WebElement leadName;
	
	public void verifyLeadPageTitle(WebDriver driver) {
		String expTitle = FileUtils.readLeadsPropertiesFile("leads.title");
		String actualTitle= getTitle(driver);
		assertEquals(expTitle,actualTitle,"Leads Page not displayed");
	}
	
	public boolean verifyLeadsViewDropdown() {
		boolean isVerified = true;
		String[] expectedOptions = FileUtils.readLeadsPropertiesFile("leads.options").split(",");
		leadsDropdown.click();
		Select actualselect = new Select(leadsDropdown);
		if(expectedOptions.length!=actualselect.getOptions().size()) {
			isVerified = false;
		}
		List<WebElement> ActualOptions=actualselect.getOptions();
		for(int i=0;i<expectedOptions.length;i++) {
			if(!expectedOptions[i].equals(ActualOptions.get(i).getText())) {
				isVerified = false;
			}
		}
		return isVerified;
	}
	
	public boolean selectDefaultView() throws InterruptedException {
		//leadsDropdown.click();
		Select actualselect = new Select(leadsDropdown);
		actualselect.selectByVisibleText(FileUtils.readLeadsPropertiesFile("leads.option.select"));
		Thread.sleep(2000);
		return verifyViewSelected();
	}
	
	public boolean verifyViewSelected() {
		boolean isSelected = false;
		Select actualselect = new Select(leadsDropdown);
		List<WebElement> selectedOption =  actualselect.getAllSelectedOptions();
		if(selectedOption.size()==1 ) {
			for(WebElement option: selectedOption) {
					if(option.getText().equals(FileUtils.readLeadsPropertiesFile("leads.option.select"))) {
						isSelected = true;
					}
			}
		}
		return isSelected;
	}
	
	public boolean verifyDefaultView(WebDriver driver){
		verifyViewSelected();
		click(goBtn);
		int status = 7;
		int len = noOfRows.getText().length();
		System.out.println(noOfRows.getText());
		int noRows = Integer.parseInt(noOfRows.getText().substring(len-2,len).trim());
		int entries = Integer.parseInt(noOfRows.getText().split(" ")[0].split("-")[1]);
		while(noRows >= leadsTable.size()) {
			System.out.println(leadsTable.size());
		for(int r=0;r<leadsTable.size();r++) {
			WebElement row = leadsTable.get(r).findElement(By.tagName("tr"));
			List<WebElement> cols = row.findElements(By.tagName("td"));
			String st = cols.get(status).getText();
			System.out.println(st);
			if(st.contains(FileUtils.readLeadsPropertiesFile("leads.status"))){
				return false;
			}
		}
		//click Next if enabled else break;
		if(!next.isEnabled()) {
			break;
		}else {
			click(next);
			WaitUtils.waitForElementInvisibility(driver,loading);
		}
		noRows = noRows - entries;
		}
		return true;
	}
	
	public boolean verifyTodaysLeadDropdown(WebDriver driver) throws InterruptedException {
		//leadsDropdown.click();
		Select actualselect = new Select(leadsDropdown);
		actualselect.selectByVisibleText(FileUtils.readLeadsPropertiesFile("leads.today.option"));
		//WaitUtils.waitForElement(driver, goBtn);
		//click(goBtn);
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("M/dd/yyyy");
		String todayDate =date.format(dtf);
		int status = 8;
		Thread.sleep(2000);
		if(!noRecords.getText().equals("No records to display.")) {
		int len = noOfRows.getText().length();
		System.out.println(noOfRows.getText());
		int noRows = Integer.parseInt(noOfRows.getText().substring(len-2,len).trim());
		int entries = Integer.parseInt(noOfRows.getText().split(" ")[0].split("-")[1]);
		while(noRows >= leadsTable.size()) {
			System.out.println(leadsTable.size());
		for(int r=0;r<leadsTable.size();r++) {
			WebElement row = leadsTable.get(r).findElement(By.tagName("tr"));
			List<WebElement> cols = row.findElements(By.tagName("td"));
			String currentDate = cols.get(status).getText();
			System.out.println(currentDate);
			if(!currentDate.equals(todayDate)){
				return false;
			}
		}
		//click Next if enabled else break;
		if(!next.isEnabled()) {
			break;
		}else {
			click(next);
			WaitUtils.waitForElement(driver, leadsTable);
			WaitUtils.waitForElementInvisibility(driver,loading);
		}
		noRows = noRows - entries;
		}
		}else {
			System.out.println("No leads available for today");
		}
		return true;
	}
	
	public boolean verifyCreateLead(WebDriver driver) {
		boolean isVerified = true;
		click(newLeadBtn);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("leads.edit.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Leads Edit Page not displayed");
		enterText(lastName,FileUtils.readOppotunitiesPropertiesFile("leads.lastname"));
		enterText(companyName,FileUtils.readOppotunitiesPropertiesFile("leads.companyname"));
		click(saveBtn);
		String expLeadTitle = FileUtils.readOppotunitiesPropertiesFile("leads.lastname");
		String ActualLeadTitle = BasePage.getTitle(driver);
		if(!ActualLeadTitle.contains(expLeadTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		String leadHeader = leadName.getText();
		if(!leadHeader.equals(FileUtils.readOppotunitiesPropertiesFile("leads.lastname"))) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
}
