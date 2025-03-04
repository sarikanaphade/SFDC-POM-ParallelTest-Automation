package Pages;

import static org.testng.Assert.assertEquals;
import java.time.DayOfWeek;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import BaseClass.BasePage;
import Utils.CommonUtils;
import Utils.FileUtils;
import Utils.WaitUtils;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import org.openqa.selenium.interactions.Actions;
import java.time.format.DateTimeFormatter;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[@class='currentStatusUserName']/a")
	public WebElement usenameLink;
	
	@FindBy(xpath="//span[@class='pageDescription']/a")
	public WebElement currentDate;
	
	@FindBy(xpath="//table[@id='calTable']/tbody/tr[2]/td[1]/div/a")
	public List<WebElement> timeTable;
	
	@FindBy(xpath="//img[@class='comboboxIcon']")
	public WebElement subjectCombo;
	
	@FindBy(xpath="//input[@id='evt5']")
	public WebElement subjectComboInput;
	
	@FindBy(xpath="//li[@class='listItem4']/a")
	public WebElement Other;
	
	@FindBy(xpath="//input[@name='EndDateTime_time']")
	public WebElement endTimeInput;
	
	@FindBy(xpath="//div[@id='simpleTimePicker']/div")
	public List<WebElement> endTimeDropdown;
	
	@FindBy(xpath="//div[@id='simpleTimePicker']/div[@class='simpleHour hover']")
	public WebElement selectedEndTime;
	
	@FindBy(xpath="//div[@id='simpleTimePicker']/div[@class='simpleHour hover']/following-sibling::div")
	public List<WebElement> selectedEndTimeDropdown;
	
	@FindBy(xpath="(//input[@name='save'])[2]")
	public WebElement saveBtn;
	
	@FindBy(xpath="//span[starts-with(@class,'event_00Uaj000001')]")
	public WebElement otherLink;
	
	@FindBy(xpath="//table[@class='detailList']/tbody/tr[5]/td[2]")
	public WebElement eventStartTime;
	//2/19/2025, 7:00 PM    
	
	@FindBy(xpath="//table[@class='detailList']/tbody/tr[6]/td[2]")
	public WebElement eventEndTime;
	//2/19/2025, 10:00 PM
	
	@FindBy(xpath="//input[@id='IsRecurrence']")
	public WebElement recSeriesCheck;

	
	@FindBy(xpath="//table[@class='recurrenceTable']")
	public WebElement recTable;
	///tbody/tr/td[1]/div/div[1]/label
	
	@FindBy(xpath="//label[@for='rectypeftw']")
	public WebElement weeklyRadio;
	
	@FindBy(xpath="//label[normalize-space()='Frequency']")
	public WebElement frequency;
	
	@FindBy(xpath="//label[@for='RecurrenceStartDateTime']")
	public WebElement recStartDate;
	
	@FindBy(xpath="//label[@for='RecurrenceEndDateOnly']")
	public WebElement recEndDate;
	
	@FindBy(xpath="//input[@id='wi']")
	public WebElement recursEvery;
	
	@FindBy(xpath="//div[@class='periodElementGroup'][2]/div[2]/input]")
	public WebElement dayCheckbox;
	
	@FindBy(name="RecurrenceEndDateOnly")
	public WebElement endDate;
	
	@FindBy(xpath="//img[@title='Month View']")
	public WebElement monthView;
	
	public void verifyHomePageTitle(WebDriver driver) {
		String expTitle = FileUtils.readHomePropertiesFile("home.title");
		String actualTitle= getTitle(driver);
		assertEquals(expTitle,actualTitle,"Home Page not displayed");
	}

	public boolean verifyHomePageDetails(WebDriver driver) {
		boolean isVerified = true;
		String uname = usenameLink.getText();
		if(!uname.equals(FileUtils.readHomePropertiesFile("home.username"))) {
			isVerified = false;
		}
		click(usenameLink);
		String expUserTitle = FileUtils.readHomePropertiesFile("home.user.title") + FileUtils.readHomePropertiesFile("home.username");
		System.out.println(expUserTitle);
		String actualUserTitle = BasePage.getTitle(driver);
		System.out.println(actualUserTitle);
		if(!actualUserTitle.contains(expUserTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		return isVerified;
	}

	public boolean verifyEditLastName(WebDriver driver) {
		boolean isVerified = true;
		UserLandingPage userLandingPage = new UserLandingPage(driver);
		click(usenameLink);
		MyProfilePage myProfilePage = new MyProfilePage(driver);
		myProfilePage.clickContactsEdit();
        myProfilePage.switchToEditProfileFrame(driver);
        if(!myProfilePage.aboutTab.isDisplayed() && myProfilePage.contactTab.isDisplayed()) {
        	isVerified=false;
        }
        myProfilePage.clickAboutTab();
        myProfilePage.editLastname(FileUtils.readHomePropertiesFile("home.newlastname"));
        myProfilePage.clickSaveAll();
        BasePage.switchToDefault(driver);
        WaitUtils.waitForTitle(driver,FileUtils.readHomePropertiesFile("home.newusername"));
        String uname = myProfilePage.userName.getText().toString();
        if(!uname.contains(FileUtils.readHomePropertiesFile("home.newlastname"))) {
        	isVerified=false;
        }
        String username = userLandingPage.userName.getText();
        if(!username.contains(FileUtils.readHomePropertiesFile("home.newlastname"))) {
        	isVerified=false;
        }
        myProfilePage.clickContactsEdit();
        myProfilePage.switchToEditProfileFrame(driver);
        if(!myProfilePage.aboutTab.isDisplayed() && myProfilePage.contactTab.isDisplayed()) {
        	isVerified=false;
        }
        myProfilePage.clickAboutTab();
        myProfilePage.editLastname(FileUtils.readHomePropertiesFile("home.lastname"));
        myProfilePage.clickSaveAll();
		return isVerified;
	}

	public boolean verifyCurrentDate() {
		String cDate = currentDate.getText();
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, yyyy");
		String formattedDate = sdf.format(currentDate);
		if(cDate.equals(formattedDate)) {
			return true;
		}
		return false;
	}

	public boolean verifyCalendarEvent(WebDriver driver) {
		boolean isVerified=true;
		click(currentDate);
		String expUserTitle = FileUtils.readHomePropertiesFile("home.calender.title") + FileUtils.readHomePropertiesFile("home.username");
		System.out.println(expUserTitle);
		String actualUserTitle = BasePage.getTitle(driver);
		System.out.println(actualUserTitle);
		if(!actualUserTitle.contains(expUserTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		
		selectStarttime(FileUtils.readHomePropertiesFile("cal.time"));
		
		String expTitle = FileUtils.readHomePropertiesFile("cal.event.title");
		String actualTitle= getTitle(driver);
		if(!actualTitle.contains(expTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Boolean hasFocus = (Boolean) jsExecutor.executeScript(
            "return arguments[0] === document.activeElement;", subjectComboInput
        );

        if (!hasFocus) {
        	CommonUtils.captureScreenshot(driver);
			isVerified = false;
        } 
		
		return isVerified;
	}
	
	

	public void selectStarttime(String stime) {
		for(WebElement time: timeTable) {
			if(time.getText().equals(stime)) {
				time.click();
				break;
			}
		}
	}
	
	public boolean verifySubjectCombo(WebDriver driver) {
		boolean isVerified=true;
		String parentHandle = driver.getWindowHandle();
		click(subjectCombo);
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles.size());
		for(String handle:windowHandles) {
			if(!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				String actTitle= driver.getTitle();
				String expTitle=FileUtils.readHomePropertiesFile("combo.title");
			
				if(actTitle.equals(expTitle)) {
					click(Other);
					break;
				}else {
					isVerified=false;
				}
			}
		}
		driver.switchTo().window(parentHandle);
		String expUserTitle = FileUtils.readHomePropertiesFile("cal.event.title");
		System.out.println(expUserTitle);
		String actualUserTitle = BasePage.getTitle(driver);
		System.out.println(actualUserTitle);
		if(!actualUserTitle.contains(expUserTitle)) {
			CommonUtils.captureScreenshot(driver);
			isVerified = false;
		}
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String subjectValue = (String) jsExecutor.executeScript(
		    "return arguments[0].value;", subjectComboInput 
		);
		
		if(!subjectValue.equals(FileUtils.readHomePropertiesFile("combo.option"))){
			isVerified=false;
		}
		return isVerified;
	}

	public boolean verifyEndTime(WebDriver driver) {
		boolean isVerified = true;
		click(endTimeInput);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime startTime = LocalTime.parse(FileUtils.readHomePropertiesFile("cal.time"), formatter);
        LocalTime expectedEndTime = startTime.plusHours(1);
        String endTimeInput = selectedEndTime.getText();
        LocalTime endTime = LocalTime.parse(endTimeInput, formatter);
        if(!endTime.format(formatter).equals(expectedEndTime.format(formatter))) {
        	isVerified=false;
        	System.out.println("correct end time mot displayed");
        }
		
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime currentTime = startTime;
        currentTime = currentTime.plusMinutes(90);
           for(WebElement endTimeDD :selectedEndTimeDropdown) {
            	String timeInput = endTimeDD.getText();
                LocalTime time = LocalTime.parse(timeInput, formatter);
                if(time.format(outputFormatter).equals(currentTime.toString())) {
//            		System.out.println(currentTime);
//            		System.out.println(currentTime.format(formatter));
                    currentTime = currentTime.plusMinutes(30);
            	}else {
            		isVerified=false;
            		System.out.println("Correct end times not displayed");
            	}
            }
          
           for(WebElement endTimeDD :endTimeDropdown) {
        	   String timeInput = endTimeDD.getText();
        	   if(timeInput.equals(FileUtils.readHomePropertiesFile("cal.end.time"))) {
        		   click(endTimeDD);
        	   }
           }

		return isVerified;
	}

	public boolean clickSaveEvent(WebDriver driver) {
		click(saveBtn);
        String expUserTitle = FileUtils.readHomePropertiesFile("home.calender.title") + FileUtils.readHomePropertiesFile("home.username");
			System.out.println(expUserTitle);
			String actualUserTitle = BasePage.getTitle(driver);
			System.out.println(actualUserTitle);
			if(!actualUserTitle.contains(expUserTitle)) {
				CommonUtils.captureScreenshot(driver);
				return false;
			}
			return true;
	}
	
	public boolean verifyEventDetails(WebDriver driver) throws InterruptedException {
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String formattedDate = today.format(formatter);
        String starttime = formattedDate+", "+FileUtils.readHomePropertiesFile("cal.time");
        String endtime = formattedDate+", "+FileUtils.readHomePropertiesFile("cal.end.time");
		Actions actions = new Actions(driver);
	    actions.moveToElement(otherLink).perform();
	    Thread.sleep(2000);
	    String actualStartTime = eventStartTime.getText();
	    System.out.println(starttime);
	    System.out.println(actualStartTime);
	    String actualendTime = eventEndTime.getText();
	    System.out.println(endtime);
	    System.out.println(actualendTime);
	    if(actualStartTime.contains(starttime)&& actualendTime.equals(endtime)) {
	    	System.out.println("Event Created");
	    	return true;
	    }
	        
		return false;
	}

	public boolean verifyRecurringOptions(WebDriver driver) throws InterruptedException {
		boolean isVerified = false;
		click(currentDate);
		selectStarttime(FileUtils.readHomePropertiesFile("cal.rec.time"));
		verifySubjectCombo(driver);
		click(endTimeInput);
		for(WebElement endTimeDD :endTimeDropdown) {
     	   String timeInput = endTimeDD.getText();
     	   if(timeInput.equals(FileUtils.readHomePropertiesFile("cal.rec.end.time"))) {
     		   click(endTimeDD);
     	   }
		}
     	   
		
		if(!recSeriesCheck.isSelected())
			click(recSeriesCheck);
		WaitUtils.waitForElement(driver, recTable);
		if(recStartDate.isDisplayed()&&recEndDate.isDisplayed()&&frequency.isDisplayed()) {
			isVerified=true;
		}
		
		click(weeklyRadio);
		if(recursEvery.getText().equals("1")) {
			isVerified=true;
		}
		DayOfWeek today = LocalDate.now().getDayOfWeek();
		String dayString=today.name();
		String capitalizedDayString = dayString.substring(0, 1) + dayString.substring(1).toLowerCase();
		String val = getCheckboxIdForDay(capitalizedDayString);
		WebElement checkbox = driver.findElement(By.xpath("//input[@id='" + val + "']"));
		if(checkbox.isSelected()) {
			isVerified=true;
		}
        
		
		 LocalDate todayDate = LocalDate.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	     LocalDate twoWeeksLater = todayDate.plusWeeks(2);
		 enterText(endDate, twoWeeksLater.format(formatter));
		 Actions actions1 = new Actions(driver);

	     actions1.moveByOffset(100, 0)  // Adjust the offset to a point outside the input box
	               .click()
	               .perform();
		 
		 clickSaveEvent(driver);
		 
		 
		    LocalDate today1 = LocalDate.now();
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy");
	        String formattedDate = today1.format(formatter1);
	        String starttime = formattedDate+", "+FileUtils.readHomePropertiesFile("cal.rec.time");
	        String endtime = formattedDate+", "+FileUtils.readHomePropertiesFile("cal.rec.end.time");
			Actions actions = new Actions(driver);
		    actions.moveToElement(otherLink).perform();
		    Thread.sleep(2000);
		    String actualStartTime = eventStartTime.getText();
		    System.out.println(starttime);
		    System.out.println(actualStartTime);
		    String actualendTime = eventEndTime.getText();
		    System.out.println(endtime);
		    System.out.println(actualendTime);
		    if(actualStartTime.contains(starttime)&& actualendTime.equals(endtime)) {
		    	System.out.println("Event Created");
		    	isVerified= true;
		    }
		    
		    click(monthView);
		    String expUserTitle = FileUtils.readHomePropertiesFile("home.calender.title") + FileUtils.readHomePropertiesFile("home.username") + FileUtils.readHomePropertiesFile("home.calender.month.title");
			System.out.println(expUserTitle);
			String actualUserTitle = BasePage.getTitle(driver);
			System.out.println(actualUserTitle);
			if(actualUserTitle.contains(expUserTitle)) {
				//CommonUtils.captureScreenshot(driver);
				isVerified= true;
			}
		return isVerified;
	}
	
	
	
	private String getCheckboxIdForDay(String day) {
        switch (day) {
            case "Monday": return "2";
            case "Tuesday": return "4";
            case "Wednesday": return "8";
            case "Thursday": return "16";
            case "Friday": return "32";
            case "Saturday": return "64";
            case "Sunday": return "1";
            default: throw new IllegalArgumentException("Invalid day: " + day);
        }
    }
	
//	// Verify the recurring event in the next two weeks (3 Fridays)
//    public static void verifyRecurringEventInNextTwoWeeks(WebDriver driver) {
//        LocalDate today = LocalDate.now();
//        LocalDate twoWeeksLater = today.plusWeeks(2);
//        List<LocalDate> fridays = getFridaysInNextTwoWeeks(today);
//
//        // Print the Fridays for debugging
//        System.out.println("Verifying events for Fridays: ");
//        for (LocalDate friday : fridays) {
//            System.out.println(friday);
//        }
//
//        monthViewButton.click();
//
//        for (LocalDate friday : fridays) {
//            verifyEventOnFriday(driver, friday);
//          //a[contains(text(),'Other')]
//          //a[contains(text(),'Other')]
//        }
//    }
//
//    // Get all Fridays in the next two weeks from today's date
//    public static List<LocalDate> getFridaysInNextTwoWeeks(LocalDate today) {
//        List<LocalDate> fridays = new ArrayList<LocalDate>();
//        
//        // Find the current Friday
//        LocalDate currentFriday = today.with(java.time.DayOfWeek.FRIDAY);
//        // Add the first Friday
//        fridays.add(currentFriday);
//
//        // Add the second Friday (one week later)
//        LocalDate nextFriday = currentFriday.plusWeeks(1);
//        fridays.add(nextFriday);
//
//        // Add the third Friday (two weeks later)
//        LocalDate thirdFriday = nextFriday.plusWeeks(1);
//        fridays.add(thirdFriday);
//        
//        return fridays;
    //}

	
}
