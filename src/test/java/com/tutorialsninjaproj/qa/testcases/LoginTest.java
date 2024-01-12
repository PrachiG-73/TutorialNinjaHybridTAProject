package com.tutorialsninjaproj.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninjaproj.qa.base.BaseClass;
import com.tutorialninjaproj.qa.util.Utilities;

public class LoginTest extends BaseClass {
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	// To load properties file we call base class's constructor
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURL(configProp.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.clickOnLogin();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "LoginTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId(email);
		loginPage.enterPassword(password);
		accountPage = loginPage.clickLoginButton();
//		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getStatusOfeditAccountInfoMessage(), "Account information not displayed.");
	}

	@DataProvider(name = "LoginTestData")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId(Utilities.generateTimeStamp());
		loginPage.enterPassword(testdataProp.getProperty("invalidPassword"));
		loginPage.clickLoginButton();

		String ActualWarningMessage = loginPage.getEmailWarningMessageText();
		String ExpectedWarningMessage = testdataProp.getProperty("emailwarningMessage");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Account credentials are Invalid.");
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailandValidPassword() {
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId(Utilities.generateTimeStamp());
		loginPage.enterPassword(configProp.getProperty("validPassword"));
		loginPage.clickLoginButton();

		String ActualWarningMessage = loginPage.getEmailWarningMessageText();
		String ExpectedWarningMessage = testdataProp.getProperty("emailwarningMessage");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Account credentials are Invalid.");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailandInValidPassword() {
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId(configProp.getProperty("validEmailId"));
		loginPage.enterPassword(testdataProp.getProperty("invalidPassword"));
		loginPage.clickLoginButton();

		String ActualWarningMessage = loginPage.getEmailWarningMessageText();
		String ExpectedWarningMessage = testdataProp.getProperty("emailwarningMessage");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Account credentials are Invalid.");
	}

	@Test(priority = 5)
	public void verifyLoginWithEmptyCredentials() {
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();

		String ActualWarningMessage = loginPage.getEmailWarningMessageText();
		String ExpectedWarningMessage = testdataProp.getProperty("emailwarningMessage");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Account credentials are Invalid.");

	}

}
