package com.tutorialninjaproj.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninjaproj.qa.util.Utilities;

public class BaseClass {

	WebDriver driver;
	public Properties configProp;
	public Properties testdataProp;

	public BaseClass() {

		configProp = new Properties();
		File propertiesFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninjaproj\\qa\\config\\config.properties");

		try {
			FileInputStream propertiesIS = new FileInputStream(propertiesFile);
			configProp.load(propertiesIS);
		} catch (IOException e) {

			e.printStackTrace();
		}

		testdataProp = new Properties();
		File testdataFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninjaproj\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream testdataIS = new FileInputStream(testdataFile);
			testdataProp.load(testdataIS);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowserAndOpenURL(String browserName) {

		if (browserName.equals("Chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(configProp.getProperty("url"));

		return driver;
	}
}
