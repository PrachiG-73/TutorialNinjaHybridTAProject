package com.tutorialsninjaproj.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountRegSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninjaproj.qa.base.BaseClass;
import com.tutorialninjaproj.qa.util.Utilities;

public class RegisterTest extends BaseClass {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountRegSuccessPage accountRegSuccessPage;

	// To load properties file we call base class's constructor
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenURL(configProp.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.clickOnRegister();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {
	//	RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(testdataProp.getProperty("firstName"));
		registerPage.enterLastName(testdataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateTimeStamp());

		registerPage.enterTelephone(testdataProp.getProperty("telephone"));
		registerPage.enterPassword(configProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(configProp.getProperty("validPassword"));

		registerPage.checkPrivacyPolicyCheckbox();
		accountRegSuccessPage = registerPage.clickContinueButton();

		String actualMessage = registerPage.accountCreationMessage();
		String successMessage = testdataProp.getProperty("accountCreationSuccessMessage");
		Assert.assertTrue(actualMessage.contains(successMessage), "Account creation failed");

		//AccountRegSuccessPage accountRegistrationSuccessPage = new AccountRegSuccessPage(driver);
		accountRegSuccessPage.clickContinueButton();

//		AccountPage accountPage = new AccountPage(driver);
//		Assert.assertTrue(accountPage.getStatusOfeditAccountInfoMessage(), "Account information not displayed.");
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithAllFields() {
	//	RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(testdataProp.getProperty("firstName"));
		registerPage.enterLastName(testdataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateTimeStamp());

		registerPage.enterTelephone(testdataProp.getProperty("telephone"));
		registerPage.enterPassword(configProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(configProp.getProperty("validPassword"));

		registerPage.clickNewsletterRadioButton();

		registerPage.checkPrivacyPolicyCheckbox();
		accountRegSuccessPage =registerPage.clickContinueButton();

		String ActualMessage = registerPage.accountCreationMessage();
		String SuccessMessage = testdataProp.getProperty("accountCreationSuccessMessage");
		Assert.assertTrue(ActualMessage.contains(SuccessMessage), "Account creation failed");

		//AccountRegSuccessPage accountRegistrationSuccessPage = new AccountRegSuccessPage(driver);
		accountRegSuccessPage.clickContinueButton();

//		AccountPage accountPage = new AccountPage(driver);
//		Assert.assertTrue(accountPage.getStatusOfeditAccountInfoMessage(), "Account information not displayed.");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingMail() {
	//	RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(testdataProp.getProperty("firstName"));
		registerPage.enterLastName(testdataProp.getProperty("lastName"));
		registerPage.enterEmail(configProp.getProperty("validEmailId"));

		registerPage.enterTelephone(testdataProp.getProperty("telephone"));
		registerPage.enterPassword(configProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(configProp.getProperty("validPassword"));

		registerPage.clickNewsletterRadioButton();
		registerPage.checkPrivacyPolicyCheckbox();
		registerPage.clickContinueButton();

		String ActualWarningMessage = registerPage.getWarningMessage();
		String ExpectedWarningMessage = testdataProp.getProperty("existingEmailIdMessage");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Account credentials are Invalid.");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithEmptyFields() {
	//	RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickContinueButton();

		String ActualPrivacyPolicyWarning = registerPage.getWarningMessage();
		String ExpectedPrivacyPolicyWarning = testdataProp.getProperty("privacyPolicyWarningMessage");
		Assert.assertTrue(ActualPrivacyPolicyWarning.contains(ExpectedPrivacyPolicyWarning),
				"Account credentials are Invalid.");

		String ActualFirstNameWarning = registerPage.getFirstNameWarningMessage();
		String ExpectedWarning = testdataProp.getProperty("firstNameWarningMessage");
		Assert.assertTrue(ActualFirstNameWarning.contains(ExpectedWarning), "Name is Invalid.");

		String ActualLastNameWarning = registerPage.getLastNameWarningMessage();
		String ExpectedLastWarning = testdataProp.getProperty("lastNameWarningMessage");
		Assert.assertTrue(ActualLastNameWarning.contains(ExpectedLastWarning), "Last Name is Invalid.");

		String ActualEmailWarning = registerPage.getEmailWarningMessage();
		String ExpectedEmailWarning = testdataProp.getProperty("emailWarningMessage");
		Assert.assertTrue(ActualEmailWarning.contains(ExpectedEmailWarning), "Email is Invalid.");

		String ActualTelephoneWarning = registerPage.getTelephoneWarningMessage();
		String ExpectedTelephoneWarning = testdataProp.getProperty("telephoneWarningMessage");
		Assert.assertTrue(ActualTelephoneWarning.contains(ExpectedTelephoneWarning), "Telephone no. is Invalid.");

		String ActualPasswordWarning = registerPage.getPasswordWarningMessage();
		String ExpectedPasswordWarning = testdataProp.getProperty("passwordWarningMessage");
		Assert.assertTrue(ActualPasswordWarning.contains(ExpectedPasswordWarning), "Password is Invalid.");

	}

}
