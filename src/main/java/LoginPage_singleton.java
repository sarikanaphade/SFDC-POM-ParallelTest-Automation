

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_singleton{

	public LoginPage_singleton() {
		PageFactory.initElements(BasePage_Singleton.getDriver(), this);
	}
	
	@FindBy(css="input#username")
	public WebElement username;
	
	@FindBy(name="pw")
	public WebElement password;
	
	@FindBy(id="Login")
	public WebElement loginButton;
	
	@FindBy(id="rememberUn")
	public WebElement remembermeCheck;
	
	@FindBy(id="error")
	public WebElement errorMsg;
	
	public void enterUsername(String name) {
		//username.sendKeys(name);
		BasePage_Singleton.enterText(username,name);
	}
	
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		loginButton.click();
		//click(loginButton);
	}
	
	public void selectRemebermeCheckbox() {
		if(remembermeCheck.isSelected()) {
			System.out.println("Checbox is already selected.");
		}else {
			remembermeCheck.click();
		}
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	
	public void rememberMeLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		selectRemebermeCheckbox();
		clickLogin();
	}
	

}
