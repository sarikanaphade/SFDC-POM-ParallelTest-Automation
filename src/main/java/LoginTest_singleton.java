

import java.io.FileNotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest_singleton {
    private LoginPage_singleton loginPage;
	//PropertyReader prop = new PropertyReader();
	
	@BeforeMethod
	public void setUp() {;  
		loginPage = new LoginPage_singleton();  // No need to initialize WebDriver manually
		BasePage_Singleton.openURL(); 
	}
	
	@Test
	public void loginErrorMsg_TC01() throws InterruptedException, FileNotFoundException {
		//loginPage.login("Heelo", "hi");
		loginPage.enterUsername("hello");
		loginPage.enterPassword("yesy");
		loginPage.clickLogin();
		//WaitUtils.waitForElement(driver, lp.errorMsg);
		String actualErrorMsg = loginPage.errorMsg.getText();
		String expectedErrorMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		if(actualErrorMsg.equals(expectedErrorMsg)) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}	
	}
}
