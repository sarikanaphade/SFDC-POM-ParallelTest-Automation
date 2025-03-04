package Pages;

import java.lang.reflect.Method;
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
import Utils.WaitUtils;

public class AccountsPage extends BasePage{
	
	public AccountsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@title='New']")
	public WebElement newAccBtn;
	@FindBy(xpath="//input[@id='acc2']")
	public WebElement accName;
	@FindBy(xpath="(//input[@title='Save'])[2]")
	public WebElement accSaveBtn;
	@FindBy(xpath="//div[@id='acc2_ileinner']")
	public WebElement accDetails;
	@FindBy(xpath="//a[contains(text(),'Create New View')]")
	public WebElement createViewLink;
	@FindBy(xpath="//input[@id='fname']")
	public WebElement viewName;
	@FindBy(xpath="//input[@id='devname']")
	public WebElement uniqueName;
	@FindBy(xpath="//select[@name='fcf']")
	public WebElement accViewDropdown;
	@FindBy(xpath="//a[text()='Edit']")
	public WebElement editView;
	@FindBy(xpath="//h2[@class='pageDescription']")
	public WebElement editViewLabel;
	@FindBy(xpath="//select[@id='fcol1']")
	public WebElement selectFilterField;
	@FindBy(xpath="//select[@id='fop1']")
	public WebElement selectOperator;
	@FindBy(xpath="//input[@id='fval1']")
	public WebElement valueInput;
	@FindBy(xpath="//select[@id='colselector_select_0']")
	public WebElement selectFieldToDisplay;
	@FindBy(xpath="//a[@id='colselector_select_0_right']")
	public WebElement addField;
	@FindBy(xpath="//div[@title='Last Activity']")
	public WebElement lastActivityColumn;
	@FindBy(xpath="//table[@class='x-grid3-row-table']")
	public WebElement viewAccountsTable;
	@FindBy(xpath="//div[@id='001aj00000txAuz_Name']/a/span")
	public WebElement viewAccName;
	@FindBy(xpath="//a[text()='Merge Accounts']")
	public WebElement mergeAccountsLink;
	@FindBy(xpath="//input[@id='srch']")
	public WebElement mergeAccInput;
	@FindBy(name="srchbutton")
	public WebElement findAccBtn;
	@FindBy(name="goNext")
	public WebElement nextBtn;
	@FindBy(xpath="//div[@class='pbWizardTitle tertiaryPalette brandTertiaryBgr']/h2")
	public WebElement step2MergeAcc;
	@FindBy(name="save")
	public WebElement mergeBtn;
	@FindBy(xpath="//table[@class='list']")
	public WebElement accList;
	@FindBy(xpath="//a[text()='Accounts with last activity > 30 days']")
	public WebElement accLastActivity;
	@FindBy(xpath="//input[@id='ext-gen20']")
	public WebElement dateFieldDropdown;
	@FindBy(xpath="//div[contains(text(),'Created Date')]")
	public WebElement createdDateOption;
	@FindBy(xpath="//button[@id='ext-gen49']")
	public WebElement saveUnSavedReportBtn;
	@FindBy(name="reportName")
	public WebElement reportName;
	@FindBy(id="saveReportDlg_DeveloperName")
	public WebElement reportUniqueName;
	@FindBy(xpath="//table[@id='dlgSaveAndRun']")
	public WebElement saveAndRunReportBtn;
	@FindBy(xpath="//img[@id='ext-gen152']")
	public WebElement fromDateBtn;
	@FindBy(xpath="//img[@id='ext-gen154']")
	public WebElement toDateBtn;
	@FindBy(xpath="(//button[text()='Today'])[1]")
	public WebElement fromDateTodayBtn;
	@FindBy(xpath="(//button[text()='Today'])[2]")
	public WebElement toDateTodayBtn;
	@FindBy(xpath="(//table[@class='x-grid3-row-table'])[1]")
	public WebElement lastActivityTable;
	@FindBy(xpath="//h1[@class='noSecondHeader pageType']")
	public WebElement unsavedReportHeader;
	@FindBy(xpath="//div[@id='status']")
	public WebElement reportGenStatus;
	@FindBy(xpath="//table[@class='reportTable tabularReportTable']")
	public WebElement reportTable;
	@FindBy(xpath="//div[@id='ext-gen253']/div")
	public List<WebElement> lastAccTableDiv;
	
	public String getAccountsPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
		
			
	public boolean verifyAccountPageTitle(WebDriver driver) {
		String expectedTitle = "Accounts";
        if(WaitUtils.waitForTitle(driver, expectedTitle))
        	return true;
        return false;
	}
	
	public void createNewAccountBtn() {
		click(newAccBtn);
	}
	
	public void enterAccountName(String accountName) {
		enterText(accName,accountName);
	}
	
	public void saveAccount() {
		click(accSaveBtn);
	}
	
	public void createNewViewBtn() {
		click(createViewLink);
	}
	
	public void enterViewName(String viewNameInput) {
		enterText(viewName,viewNameInput);
	}
	
	public void enterUniqueName(String uniqueNameInput) {
		enterText(uniqueName,uniqueNameInput);
	}
	
	public List<WebElement> getSelectViewOptions(){
		Select select = new Select(accViewDropdown);
		return select.getOptions();
	}
	
	public WebElement getSelectedView() {
		Select select = new Select(accViewDropdown);
		return select.getFirstSelectedOption();
	}
	
	public void selectView(String vName) {
		Select select = new Select(accViewDropdown);
		select.selectByVisibleText(vName);
	}
	
	public void clickEditView() {
		click(editView);
	}
	
	public void selectField(String fName) {
		Select select = new Select(selectFilterField);
		select.selectByVisibleText(fName);
	}
	
	public void selectOperator(String oName) {
		Select select = new Select(selectOperator);
		select.selectByVisibleText(oName);
	}
	
	public void enterValue(String val) {
		enterText(valueInput,val);
	}
	
	public void selectFieldToDisplay(String fieldName) {
		Select select = new Select(selectFieldToDisplay);
		select.selectByVisibleText(fieldName);
	}
	
	public void addField() {
		click(addField);
	}
	
	public void clickMergeAccount() {
		click(mergeAccountsLink);
	}
	
	public void clickMergeAccInput(String accName) {
		enterText(mergeAccInput,accName);
	}
	
	public void clickFindAccount() {
		click(findAccBtn);
	}
		
	public void clickNextBtn() {
		click(nextBtn);
	}
	
	public void clickMergeBtn() {
		click(mergeBtn);
	}
	
	public boolean verifyCreateAccountReport(WebDriver driver) {
		click(accLastActivity);
		if(WaitUtils.waitForTitle(driver,"Unsaved Report")) {
			return true;
		}
		return false;
	}
	
	public boolean verifyReportOptions(WebDriver driver,Method name) throws InterruptedException {
		boolean isAvailable = true;
		click(dateFieldDropdown);
		click(createdDateOption);
		click(fromDateBtn);
		click(fromDateTodayBtn);
		click(toDateBtn);
		click(toDateTodayBtn);
		Thread.sleep(2000);
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("M/dd/yyyy");
		String currentDate =date.format(dtf);
		//String currentDate = getTodaysDate();
		//WaitUtils.waitForElement(driver, lastAccTableDiv);
		int noOfRows = lastAccTableDiv.size()-1;
		for(int i=0;i<noOfRows;i++) {
			WebElement row = lastActivityTable.findElement(By.tagName("tr"));
			List<WebElement> cols = row.findElements(By.tagName("td"));
			System.out.println("Number of columns: " + cols.size());  
			int dateField = 6;
			String lastModifiedDate =cols.get(dateField).getText();
			System.out.println(lastModifiedDate);
			if(!currentDate.equals(lastModifiedDate)) {
				isAvailable = false;
				break;
			}			
		}
		CommonUtils.captureScreenshot(driver,name.getName());
		return isAvailable;
	}
	
	public boolean verifySaveReport(WebDriver driver) throws InterruptedException {
		boolean isAvailable = false;
		click(saveUnSavedReportBtn);
		enterText(reportName,"Test_Report2");
		Thread.sleep(2000);
		reportUniqueName.clear();
		enterText(reportUniqueName,"Test_Report2");
		//WaitUtils.waitForElement(driver, saveAndRunReportBtn);
		WaitUtils.waitForVisibilityOfElement(driver,saveAndRunReportBtn);
		Thread.sleep(4000);
		//click(saveAndRunReportBtn);
		clickUsingJS(driver,saveAndRunReportBtn);
		if(WaitUtils.waitForTitle(driver,"Test_Report2")) {
			isAvailable = true;
		}
		String reportName = unsavedReportHeader.getText();
		String repGenStatus = reportGenStatus.getText();
		List<WebElement> rows = reportTable.findElements(By.tagName("tr"));
		if(reportName.equals("Test Report1") && repGenStatus.equals("Complete") && (rows.size() >=1)) {
			isAvailable = true;
		}
		return isAvailable;
	}
	

	public String getTodaysDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");
		String formattedDate = currentDate.format(formatter);
    	return formattedDate;
	}
	


	

}
