package Utils;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WaitUtils {
	public static boolean waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		if(element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean waitForElementInvisibility(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public static boolean waitForTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	public static void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForPresenceOfElementLocated(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}
	
	public static boolean waitForPresenceOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.presenceOfElementLocated((By) element))!=null;
	}
	
	public static void waitForPageLoad(WebDriver driver) {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Wait for up to 30 seconds
	        wait.until(d -> {
	            JavascriptExecutor js = (JavascriptExecutor) d;
	            String readyState = (String) js.executeScript("return document.readyState;");
	            return readyState.equals("complete");
	        });
	    }

	
	public static boolean waitForElement(WebDriver driver, List<WebElement> elements) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		if(elements.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
