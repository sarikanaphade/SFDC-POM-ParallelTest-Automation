package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseClass.BasePage;

public class MyProfilePage extends BasePage{
	public MyProfilePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@class='contactInfoLaunch editLink']")
	public WebElement contactsEdit;
	
	@FindBy(xpath="//iframe[@id='contactInfoContentId']")
	public WebElement editProfileFrame;
	
	@FindBy(xpath="//li[@id='aboutTab']")
	public WebElement aboutTab;
	
	@FindBy(xpath="//li[@id='contactTab']")
	public WebElement contactTab;
	
	@FindBy(name="lastName")
	public WebElement lastname;
	
	@FindBy(xpath="//input[@value='Save All']")
	public WebElement saveAll;
	
	@FindBy(id="publisherAttachTextPost")
	public WebElement postLink;
	
	@FindBy(xpath="//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement postShareFrame;
	
	@FindBy(xpath="//body[@role='textbox']")
	public WebElement shareTextbox;
	
	@FindBy(id="publishersharebutton")
	public WebElement shareButton;
	
	@FindBy(xpath="//span[@class='feeditemtext cxfeeditemtext']/p")
	public WebElement sharedDisplayText;
	
	@FindBy(xpath="(//span[@class='publisherattachtext '])[2]")
	public WebElement fileLink;
	
	@FindBy(id="chatterFile")
	public WebElement chooseFile;
	
	@FindBy(xpath="//a[@id='chatterUploadFileAction']")
	public WebElement uploadFile;
	
	@FindBy(xpath="//div[@class='contentFileTitle']")
	public WebElement sharedFileTitle;
	
	@FindBy(xpath="(//img[@class='chatter-photo'])[1]")
	public WebElement profileImage;
	
	@FindBy(xpath="//a[@id='uploadLink']")
	public WebElement addPhotoLink;
	
	@FindBy(id="uploadPhotoContentId")
	public WebElement uploadProfilePhotoFrame;
	
	@FindBy(xpath="//input[@id='j_id0:uploadFileForm:uploadInputFile']")
	public WebElement uploadProfilePhotoChooseFile;
	
	@FindBy(xpath="//input[@id='j_id0:uploadFileForm:save']")
	public WebElement saveButton;
	
	@FindBy(xpath="//input[@value='Cancel']")
	public WebElement cancelButton;
	
	@FindBy(xpath="//span[@id='tailBreadcrumbNode']")
	public WebElement userName;
	
	public void clickContactsEdit() {
		click(contactsEdit);
	}
	
	public void clickAboutTab() {
		//switchToFrame(editProfileFrame);
		click(aboutTab);
	}
	
	public void editLastname(String lastName) {
		enterText(lastname,lastName);
	}
	
	public void clickSaveAll() {
		click(saveAll);
	}
	
	public void clickPostLink() {
		click(postLink);
	}
	
	public void EnterPostText(WebDriver driver, String postText) {
		switchToFrame(driver,postShareFrame);
		enterText(shareTextbox,postText);
	}
	
	public void switchToEditProfileFrame(WebDriver driver) {
		switchToFrame(driver,editProfileFrame);
	}
	
	public static void switchToParentFrame(WebDriver driver) {
		switchToDefault(driver);
	}
	
	public void clickShare() {
		click(shareButton);
	}
	public void clickFileLink() {
		click(fileLink);
	}
	
	public void uploadFile(String fileName) {
		click(uploadFile);
		chooseFile.sendKeys(fileName);
	}
	
	public void goToProfileImage(WebDriver driver) {
		moveToElement(driver,profileImage);
	}
	
	public void clickAddPhoto() {
		click(addPhotoLink);
	}
	
	public void uploadProfilePhoto(WebDriver driver,String fileName) {
		switchToFrame(driver,uploadProfilePhotoFrame);
		uploadProfilePhotoChooseFile.sendKeys(fileName);
		//click(cancelButton);
		click(saveButton);
	}
	
	public void clickSave() {
		//switchToFrame(uploadProfilePhotoFrame);
		click(saveButton);
	}
}
