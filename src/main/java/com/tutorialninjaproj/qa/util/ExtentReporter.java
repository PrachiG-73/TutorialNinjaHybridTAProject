package com.tutorialninjaproj.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	
	public static ExtentReports generateExtentReports() {
		
		ExtentReports extentReport =new ExtentReports();
		
		File extentReportfile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportfile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("TN Automation Report");
	    sparkReporter.config().setReportName("TutorialNinja Automation Report");
	    sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
	    
	    extentReport.attachReporter(sparkReporter);
	    
	    Properties configproperties = new Properties();
	    File configPropfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninjaproj\\qa\\config\\config.properties");
	    FileInputStream fis;
		try {
			fis = new FileInputStream(configPropfile);		    
		    configproperties.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	    extentReport.setSystemInfo("Application URL", configproperties.getProperty("url"));
	    extentReport.setSystemInfo("Browser", configproperties.getProperty("browser"));
	    extentReport.setSystemInfo("Application URL", configproperties.getProperty("validEmailId"));
	    extentReport.setSystemInfo("Application URL", configproperties.getProperty("validPassword"));
	    /*print properties of system with following code
	    System.getProperties().list(System.out);*/
	    extentReport.setSystemInfo("Operation System", System.getProperty("os.name"));
	    extentReport.setSystemInfo("Username", System.getProperty("user.name"));
	    extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	    return extentReport;
	}
	
}
