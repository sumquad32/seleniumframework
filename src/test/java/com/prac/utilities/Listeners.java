package com.prac.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

	private static ExtentReports extent = null;
	ExtentTest test = null;
	String testName = null;

	@Override
	public void onStart(ITestContext context) {

		extent = ExtentManager.generateReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		testName = result.getName();
		test = extent.createTest(testName);
		test.log(Status.INFO, "Test is executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, testName + " successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String destPath = Utility.takeScreenshot(driver, testName);
		test.addScreenCaptureFromPath(destPath);
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.FAIL, testName + " is failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.INFO, result.getThrowable());
		test.log(Status.SKIP, testName + " is skipped");

	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
