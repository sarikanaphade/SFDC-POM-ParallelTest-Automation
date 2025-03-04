package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Constants.FileConstant;

public class ReportManager {
	
	private static ExtentReports report;
	
	public static ExtentReports getInstance() {
		if(report==null) {
			createInstance();
		}
		return report;
	}

	private static ExtentReports createInstance() {
		ExtentSparkReporter sr = new ExtentSparkReporter(FileConstant.REPORT_PATH);
		sr.config().setDocumentTitle("SFDC Report - "+CommonUtils.getTimeStamp());
		sr.config().setTheme(Theme.STANDARD);
		report = new ExtentReports();
		report.attachReporter(sr);
		return report; 
		
	}

}
