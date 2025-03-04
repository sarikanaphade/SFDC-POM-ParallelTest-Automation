package Listners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClass.BaseTest;
import Utils.CommonUtils;

public class TestListners implements ITestListener{
	
	public void onTestSuccess(ITestResult result) {
		BaseTest.threadLocalTest.get().pass("Test : "+ result.getName()+" passed!");
	}
	
	public void onTestStart(ITestResult result) {
		//BaseTest.test = BaseTest.report.createTest(result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		BaseTest.threadLocalTest.get().fail("Test : "+ result.getName()+" failed!");
		BaseTest.threadLocalTest.get().addScreenCaptureFromPath(CommonUtils.
				captureScreenshot(BaseTest.threadLocalDriver.get()));
	}
	
	public void onTestSkipped(ITestResult result) {
		BaseTest.threadLocalTest.get().skip("Test skipped: " + result.getName());
    }

}
