package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninjaproj.qa.util.ExtentReporter;
import com.tutorialninjaproj.qa.util.Utilities;

public class myListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
		 extentReport =	ExtentReporter.generateExtentReports();
	}
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
	    extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, "Execution of " +testName+ " starts..");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName+" passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		extentTest.log(Status.INFO,"Takes screenshot");
		String destScreenshot= Utilities.getScrrenshotDestination(driver, result.getName());		
		extentTest.addScreenCaptureFromPath(destScreenshot);
		
		extentTest.log(Status.FAIL,testName+" failed");
		extentTest.log(Status.INFO,result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName+" skipped");
	}	

	@Override
	public void onFinish(ITestContext context) {
		extentTest.log(Status.INFO,"Execution finished");
		extentReport.flush();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
