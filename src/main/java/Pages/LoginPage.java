package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import com.aventstack.extentreports.Status;
import BaseClass.BasePage;
import BaseClass.BaseTest;
//import BaseClass.BaseTest;

/**
 * This class has all the WebElements and actions related to Login Page
 * @author Sarika Naphade
 * 
 */
public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="input#username")
	public WebElement username;
	
	@FindBy(name="pw")
	public WebElement password;
	
	@FindBy(id="Login")
	public WebElement loginButton;
	
	@FindBy(id="rememberUn")
	public WebElement remembermeCheck;
	
	@FindBy(xpath="//div[@id='idcard']")
	public WebElement rememberUserName; 
	
	@FindBy(id="error")
	public WebElement errorMsg;
	
	@FindBy(id="forgot_password_link")
	public WebElement forgotPassword;
	
	public void enterUsername(String name) {
		enterText(username,name);
	}
	
	public void enterPassword(String pwd) {
		enterText(password,pwd);
	}
	
	public void clickLogin() {
		click(loginButton);
	}
	
	
	/**
	 * This Method is used for clicking on forgot password link on Login page
	 * @param driver - WebDriver
	 * @return - Forgot Password object
	 */
	public ForgotPasswordPage clickforgotPassword(WebDriver driver) {
		click(forgotPassword);
		BaseTest.threadLocalTest.get().info("Forgot password link clicked");
		return new ForgotPasswordPage(driver);
	}
	
	
	/**
	 * This method is used for selecting Remember Me checkbox.
	 */
	public void selectRemebermeCheckbox() {
		if(remembermeCheck.isSelected()) {
			System.out.println("Checbox is already selected.");
		}else {
			click(remembermeCheck);
		}
	}
	
	
	/**
	 * This method is used to login in to the SFDC application.
	 * @param driver - type of WebDriver
	 * @param uname - username
	 * @param pwd - password
	 * @return - returns User Landing Page object
	 */
	public UserLandingPage login(WebDriver driver,String uname, String pwd) {
		enterText(username, uname);
		BaseTest.threadLocalTest.get().info("Entered username: "+uname);
		enterText(password,pwd);
		BaseTest.threadLocalTest.get().info("Entered password");
		click(loginButton);
		BaseTest.threadLocalTest.get().info("Login Button clicked ");
		return new UserLandingPage(driver);
	}
	
	/**
	 * This method is used to login using Remember Me in to the SFDC application.
	 * @param driver - type of WebDriver
	 * @param uname - username
	 * @param pwd - password
	 * @return - return User Landing Page object
	 */
	public UserLandingPage rememberMeLogin(WebDriver driver,String uname, String pwd) {
		enterText(username, uname);
		BaseTest.threadLocalTest.get().info("Entered username: "+uname);
		enterText(password,pwd);
		BaseTest.threadLocalTest.get().info("Entered password");
		selectRemebermeCheckbox();
		BaseTest.threadLocalTest.get().info("Remeber me checkbox checked");
		click(loginButton);
		BaseTest.threadLocalTest.get().info("Login Button clicked ");
		return new UserLandingPage(driver);
	}
	
	
	/**
	 * This method used for fetching the login page title
	 * @param driver - type of WebDriver 
	 * @return - Login Page title
	 */
	public String getLoginPageTitle(WebDriver driver) {
		return driver.getTitle();
	}


}
