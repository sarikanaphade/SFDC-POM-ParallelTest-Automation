

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class BasePage_Singleton {

	protected static WebDriver driver;
	
	private BasePage_Singleton() {
	}
	
	public static void openURL() {
//		if (driver == null) {
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
//		}
		getDriver().get("https://login.salesforce.com/");
	}
	
	public static void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	
//	public void navigateToLoginPage(){
//		 driver.manage().window().maximize();
//		 driver.navigate().to("https://login.salesforce.com/");
//		 waitForPageLoad(driver);
//		 verifyTitle("Login | Salesforce");
//	}
//	
//	public  void login(String username, String password, boolean rememberMe){
//		WebElement uname = findElement("css","input#username");
//		WebElement pwd = findElement("name","pw");
//		WebElement loginButton = findElement("id","Login");
//		enterText(uname,username);
//		String decryptpwd="";
//		try {
//			decryptpwd = decryptPassword(password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		enterText(pwd,decryptpwd);
//		checkRememberMe(rememberMe);
//		click(loginButton);
//		waitForPageLoad(driver);
//	}
//	
//	public  void forgotPassword() {
//		WebElement fpl= findElement("id","forgot_password_link");
//		click(fpl);
//		WebElement un = findElement("id","un");
//		//enterText(un,getPropertyValue("username"));
//		WebElement cont = findElement("id","continue");
//		click(cont);
//		WebElement emailMessage = findElement("xpath","//h1[@class='mb12']");
//		if(emailMessage.getText().equals("Check Your Email")){
//			System.out.println("Email sent.");
//		}else {
//			System.out.println("Error while resetting password!!!");
//		}
//	}
//	
//	
	public static void enterText(WebElement element, String text) {
		if(element.isDisplayed() && text!=null) {
			element.clear();
			element.sendKeys(text);
		}else {
			System.out.println("Error while entering text!!");
		}
	}
//	
//	public void click(WebElement element) {
//		if(element.isDisplayed() && element.isEnabled()) {
//			element.click();
//		}else {
//			System.out.println("Cannot able to click on element!!");
//		}
//	}
//
//	public WebElement findElement(String locator,String value) {
//		WebElement element = null;
//		try {
//		if(locator!=null) {
//			if(locator.equalsIgnoreCase("id")) {
//				element = driver.findElement(By.id(value));
//			}else if(locator.equalsIgnoreCase("name")) {
//				element = driver.findElement(By.name(value));
//			}else if(locator.equalsIgnoreCase("className")) {
//				element = driver.findElement(By.className(value));
//			}else if(locator.equalsIgnoreCase("tagName")) {
//				element = driver.findElement(By.tagName(value));
//			}else if(locator.equalsIgnoreCase("xpath")) {
//				element = driver.findElement(By.xpath(value));
//			}else if(locator.equalsIgnoreCase("css")) {
//				element = driver.findElement(By.cssSelector(value));
//			}else{
//				System.out.println("Element with locator "+ locator + " and value "+ value +" not found");
//			}
//			}
//		}catch(NoSuchElementException e) {
//			System.out.println("Element not present with the specified locator "+ locator+" and value " + value);
//		}
//		return element;
//	}
//	
//	public void waitForPageToLoad(String PageTitle) {
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.titleContains(PageTitle));
//	}
//	
//	public void waitForElement(WebElement element) throws InterruptedException {
//		Thread.sleep(3000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.elementToBeClickable(element));
//	}
//	
//	public void waitForPageLoad(WebDriver driver) {
//	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for up to 30 seconds
//	        wait.until(d -> {
//	            JavascriptExecutor js = (JavascriptExecutor) d;
//	            String readyState = (String) js.executeScript("return document.readyState;");
//	            return readyState.equals("complete");
//	        });
//	    }
//
//	
//	public boolean verifyErrorMessage(String errorMsg) {
//		WebElement actualerroMsg = findElement("id","error");
//		return actualerroMsg.getText().equals(errorMsg);
//	}
//	
//	public  void verifyTitle(String Title) {
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.titleContains(Title));
//		if (driver.getTitle().contains(Title)) {
//			System.out.println("Navigated to "+ Title +" page."); 
//		}else {
//			System.out.println(Title + " mismatched!!!");
//		}
//	}
//	
//	public boolean verifyUserName(String uname) {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		WebElement actualerroMsg = findElement("id","userNavLabel");
//		return actualerroMsg.getText().equals(uname);
//	}
//	
//	public void selectFromProfileDropdown(String choice) {
//		WebElement dropdown = findElement("id","userNav-arrow");
//		click(dropdown);
//		WebElement selectChoice = findElement("xpath","//a[contains(text(),'"+choice+"')]");
//		click(selectChoice);
//	}
//	
//	public void clickOnUserMenu() {
//		WebElement dropdown = findElement("id","userNav-arrow");
//		click(dropdown);
//	}
//	
//	public void checkRememberMe(boolean choice) {
//		WebElement remMe = findElement("id","rememberUn");
//		if(!remMe.isSelected() && choice == true) {
//			click(remMe);
//		}else if(remMe.isSelected() && choice == false){
//			click(remMe);
//			System.out.println("checkbox is not selected.");
//		}
//	}
//	
//	public void logout() {
//		selectFromProfileDropdown("Logout");
//		System.out.println("Logged out.");
//		waitForPageLoad(driver);
//		verifyTitle("Login | Salesforce");
//	}
//	
//	public static String decryptPassword(String password) throws Exception {
//		EncryptDecryptPassword edpwd = new EncryptDecryptPassword();
//		return(edpwd.decrypt(password));
//	}
//	
//	public  void editProfile(String newlastname) {
//		WebElement editProfile = findElement("xpath","//a[@class='contactInfoLaunch editLink']");
//		click(editProfile);
//		WebElement fr = findElement("xpath","//iframe[@id='contactInfoContentId']");
//		driver.switchTo().frame(fr);
//		WebElement aboutLink = findElement("xpath"," //li[@id='aboutTab']");
//		click(aboutLink);
//		WebElement lastname = findElement("xpath","//input[@name='lastName']");
//		enterText(lastname,newlastname);
//		WebElement saveAll = findElement("xpath","//input[@value='Save All']");
//		click(saveAll);
//		driver.switchTo().defaultContent();
//	}
}
