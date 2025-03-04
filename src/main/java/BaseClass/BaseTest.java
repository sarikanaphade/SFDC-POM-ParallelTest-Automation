package BaseClass;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.*;

public class BaseTest {

	    public static ExtentReports report=null;
	    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	    public static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>();
	    
	    @BeforeSuite
	    public static void setUp1() {
	    	report = ReportManager.getInstance();
	    }
	    
	    
	    public static void setDriver(String bname, boolean headless) {
	    	WebDriver driver = getBrowserDriver("Chrome",false);
	    	threadLocalDriver.set(driver);
	    }
	    
	    public static WebDriver getDriver() {
	    	return threadLocalDriver.get();
	    }
	    
	    @BeforeMethod
		public void setupBrowser(Method name) {
			BaseTest.setDriver("Chrome", false);
			ExtentTest test = report.createTest(name.getName());
			threadLocalTest.set(test);
		}
	    
	    @AfterMethod
	    public void closeDriver() {
	    	getDriver().quit();
	    	threadLocalDriver.remove();
	    }

	    public static WebDriver getBrowserDriver(String bname, boolean headless) {
	    	String browserName = bname.toLowerCase();
	    	WebDriver driver = null;
	    	switch(browserName){
	    	case "chrome":
	    		if(headless) {
	    			ChromeOptions co = new ChromeOptions();
	    			co.addArguments("--headless");
	    			driver = new ChromeDriver(co);
	    		}
	    		driver = new ChromeDriver();
	    		break;
	    	case "firefox" :
	    		driver = new FirefoxDriver();
	    		break;
	    	case "safari":
	    		driver = new SafariDriver();
	    		break;
	    	default:
	    		System.out.println("Only Chrome, Safari and firefox browsers are supported.");
	    		break;
	    	}
	    	driver.manage().window().maximize();
	    	return driver;
	    }
	    
//	    public static void initialization() {
//	        driver.get("https://Login.salesforce.com");
//	    }
//	    
//	    public static void quitBrowser() {
//	    	if (driver != null) {
//			driver.quit();
//	    	}
//		}
//		
//	    public static void closeBrowser() {
//	    	if (driver != null) {
//			driver.close();
//	    	}
//		}
//	    
		
		public static String getPropertyValue(String key) {
			String value="";
			value = FileUtils.readfile().getProperty(key);
			return value;
		}
		
		
		public static String decryptPassword(String password){
			String pwd="";
			try {
				pwd = EncryptDecryptPassword.decrypt(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pwd;
		}
		
		@AfterSuite
		public static void tearDown1() {
			report.flush();
		}
		
}
