package Utils;

import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import Constants.FileConstant;
import java.io.File;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class CommonUtils {
	
	public static String captureScreenshot(WebDriver driver) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		String fileName = FileConstant.SCREENSHOT_PATH+"Output_"+getTimeStamp()+".png";
		File dest = new File(fileName);
		File src  = tss.getScreenshotAs(OutputType.FILE);
		src.renameTo(dest);
		return fileName;
	}
	
	public static void captureScreenshot(WebDriver driver,String testName) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		String fileName = FileConstant.SCREENSHOT_PATH+testName+"_"+getTimeStamp()+".png";
		File dest = new File(fileName);
		File src  = tss.getScreenshotAs(OutputType.FILE);
		src.renameTo(dest);
	}
	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

	
}
