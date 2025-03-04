package BaseClass;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;


public class BasePage {
	
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public static void enterText(WebElement element, String text) {
		if(element.isDisplayed() && text!=null) {
			element.clear();
			element.sendKeys(text);
		}else {
			System.out.println("Error while entering text!!");
		}
	}
	
	public static void click(WebElement element) {
		try {
		if(element.isDisplayed() && element.isEnabled()) {
			element.click();
			}
		}catch (StaleElementReferenceException e) {
		//	WebElement myElement = driver.findElement(By.id("elementID"));
			element.click();
		}
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].click();", element);
			//ystem.out.println("Trying with Java script executor - Cannot able to click on element!!");
		
	}
	
	public static void clickUsingJS(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void switchToFrame(WebDriver driver,WebElement frame) {
		if(frame!=null) {
			driver.switchTo().frame(frame);
		}
	}
	
	public static void switchToDefault(WebDriver driver) {
			driver.switchTo().defaultContent();
	}
	
	public static Alert switchToAlert(WebDriver driver) {
		Alert al =driver.switchTo().alert();
		return al;
}
	
	public static void switchToWindow(WebDriver driver,String handle) {
		if(handle!=null)
			driver.switchTo().window(handle);
	}
	
	public static String getParentWindow(WebDriver driver) {
		String parentWindow = "";
		if(driver!= null)
			parentWindow = driver.getWindowHandle();
		return parentWindow;
	}
	
	public static Set<String> getAllWindowHandles(WebDriver driver){
		Set<String> windowHandles = null;
		if(driver!= null)
			windowHandles = driver.getWindowHandles();
		return windowHandles;
	}
	
	public static void moveToElement(WebDriver driver,WebElement element) {
		Actions action = new Actions(driver);
		if(element.isDisplayed()) {
			action.moveToElement(element).perform();
		}
	}
    
	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
//		if (driver.getTitle().contains(title)) {
//			System.out.println("Title Matches"); 
//		}else {
//			System.out.println("Title not matched!!");
//		}
	}

}
