package Pages;

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

public class OpportunitiesPage extends BasePage{

	public OpportunitiesPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//select[@id='fcf']")
	public WebElement oppViewDropdown;
	
	@FindBy(name="new")
	public WebElement createNewOpp;
	
	@FindBy(xpath="//h2[@class='mainTitle']")
	public WebElement oppEditTitle;
	
	@FindBy(id="opp3")
	public WebElement oppName;
	
	@FindBy(id="opp4")
	public WebElement oppAccName;
	
	@FindBy(xpath="//input[@id='opp9']")
	public WebElement closedDateBtn;
	
	@FindBy(xpath="//a[@class='calToday']")
	public WebElement closedDateToday;
	
	@FindBy(xpath="//select[@id='opp11']")
	public WebElement stageDropdown;
	
	@FindBy(xpath="//input[@id='opp12']")
	public WebElement probability;
	
	@FindBy(xpath="//select[@id='opp6']")
	public WebElement leadSourceDropdown;
	
	@FindBy(xpath="//input[@id='opp17']")
	public WebElement primaryCampaignSource;
	
	@FindBy(xpath="(//input[@title='Save'])[2]")
	public WebElement saveOppBtn;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	public WebElement oppNameHeader;
	
	@FindBy(xpath="//a[text()='Opportunity Pipeline']")
	public WebElement oppPipelineLink;
	
	@FindBy(xpath="//div[@id='status']")
	public WebElement reportGenStatus;
	
	@FindBy(xpath="//table[@class='reportTable tabularReportTable']")
	public WebElement oppPipelineReportTable;
	
	@FindBy(xpath="//a[text()='Stuck Opportunities']")
	public WebElement stuckOppLink;
	
	@FindBy(xpath="//select[@id='quarter_q']")
	public WebElement quarterSummaryInterval;
	
	@FindBy(xpath="//select[@id='open']")
	public WebElement quarterSummaryIncludeDropdown;
	
	@FindBy(xpath="//input[@title='Run Report']")
	public WebElement runReportBtn;
	
	
	/**
	 * This method used to verify Opportunity dropdown options
	 * @param driver - webdriver
	 * @return True - if expected options available
	 * 		   False - if expected options not found.
	 */
	public boolean verifyOppDropdown(WebDriver driver) {
		boolean isVerified = true;
		String[] expectedOptions = FileUtils.readOppotunitiesPropertiesFile("opp.view.options").split(",");
		Select actualselect = new Select(oppViewDropdown);
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
	
	
	public void verifyEditPage(WebDriver driver) {
		click(createNewOpp);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("opp.edit.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(!expTitle.contains(ActualTitle),"Edit Page not displayed");
	}
	
	public boolean verifyCreateNewOpportunity(WebDriver driver) {
		boolean isVerified = true;
		enterText(oppName,FileUtils.readOppotunitiesPropertiesFile("opp.name"));
		enterText(oppAccName,FileUtils.readOppotunitiesPropertiesFile("opp.acc.name"));
		click(closedDateBtn);
		click(closedDateToday);
		Select stageDD = new Select(stageDropdown);
		stageDD.selectByVisibleText(FileUtils.readOppotunitiesPropertiesFile("opp.stage.option"));
		enterText(probability,FileUtils.readOppotunitiesPropertiesFile("opp.probability"));
		Select leadSource = new Select(leadSourceDropdown);
		leadSource.selectByVisibleText(FileUtils.readOppotunitiesPropertiesFile("opp.leadsource"));
		click(saveOppBtn);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("opp.name");
		String ActualTitle = BasePage.getTitle(driver);
		if(!ActualTitle.contains(expTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		String oppHeader = oppNameHeader.getText();
		if(!oppHeader.equals(FileUtils.readOppotunitiesPropertiesFile("opp.name"))) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
	public boolean verifyOppPipelineReport(WebDriver driver) {
		boolean isVerified = true;
		click(oppPipelineLink);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("opp.pipeline.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(ActualTitle.contains(expTitle),"Stuck Apportunity report not displayed");
		List<WebElement> rows = oppPipelineReportTable.findElements(By.tagName("tr"));
		System.out.println(rows.size());
		if(rows.size()<1) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
	public boolean verifyStuckOppReport(WebDriver driver) {
		boolean isVerified = true;
		click(stuckOppLink);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("opp.stuck.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(ActualTitle.contains(expTitle),"Stuck Apportunity report not displayed");
		List<WebElement> rows = oppPipelineReportTable.findElements(By.tagName("tr"));
		if(rows.size()<1) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
	public boolean verifySummaryReport(WebDriver driver) {
		boolean isVerified = true;
		Select quarterInterval = new Select(quarterSummaryInterval);
		quarterInterval.selectByVisibleText(FileUtils.readOppotunitiesPropertiesFile("opp.interval"));
		Select quarterInclude = new Select(quarterSummaryIncludeDropdown);
		quarterInclude.selectByVisibleText(FileUtils.readOppotunitiesPropertiesFile("opp.include"));
		click(runReportBtn);
		String expTitle = FileUtils.readOppotunitiesPropertiesFile("opp.report.title");
		String ActualTitle = BasePage.getTitle(driver);
		assertTrue(ActualTitle.equals(expTitle),"Quarterly summary report not displayed");
		List<WebElement> rows = oppPipelineReportTable.findElements(By.tagName("tr"));
		if(rows.size()<1) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}
	
}
