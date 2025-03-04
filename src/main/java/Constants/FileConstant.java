package Constants;

import Utils.CommonUtils;

//import Utils.CommonUtils;

public class FileConstant {
	public final static String ROOT_PATH = System.getProperty("user.dir");
//	public final static String SCREENSHOT_PATH = ROOT_PATH + "/src/main/resources/Reports/Screenshots/"
//						+CommonUtils.getTimeStamp()+ ".PNG";
	public final static String SCREENSHOT_PATH = ROOT_PATH + "/src/main/resources/Reports/Screenshots/";
	public final static String LOGIN_PAGE_TEST_DATA_PATH= ROOT_PATH + "/src/main/java/InputData/loginPage.properties";
	public final static String OPPORTUNITIES_PAGE_TEST_DATA_PATH= ROOT_PATH + "/src/main/java/InputData/opportunitiesPage.properties";
	public final static String LEADS_PAGE_TEST_DATA_PATH= ROOT_PATH + "/src/main/java/InputData/leadsPage.properties";
	public final static String CONTACTS_PAGE_TEST_DATA_PATH= ROOT_PATH + "/src/main/java/InputData/contactsPage.properties";
	public final static String HOME_PAGE_TEST_DATA_PATH= ROOT_PATH + "/src/main/java/InputData/homePage.properties";
	public static final String REPORT_PATH =  ROOT_PATH + "/src/main/resources/Reports/"
			+"SFDC Report - "+CommonUtils.getTimeStamp();
			
}
