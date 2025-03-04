package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseClass.BasePage;

public class ForgotPasswordPage extends BasePage{
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="un")
	public WebElement username;
	
	@FindBy(id = "continue")
	public WebElement continueButton;
	
	@FindBy(xpath="//h1[@class='mb12']")
	public WebElement emailMessage;
	
	public void enterUsername(String uname) {
		enterText(username,uname);
	}
	
	public void clickcontinueButton() {
		click(continueButton);
	}
	
}
