package com.tutorialsninjaproj.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.ProductPage;
import com.tutorialninjaproj.qa.base.BaseClass;

public class SearchTest extends BaseClass {

	public WebDriver driver;
	HomePage homePage;
	ProductPage productPage;

	// To load properties file we call base class's constructor
	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenURL(configProp.getProperty("browser"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchFieldWithValidProduct() {
//		HomePage homePage = new HomePage(driver);
		homePage.enterTextInSearchField(testdataProp.getProperty("validProductName"));
		productPage = homePage.clickSearchButton();

//		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.checkIfProductIsDisplayed(), "Product not found");
	}

	@Test(priority = 2)
	public void verifySearchFieldWithInvalidProduct() {
//		HomePage homePage = new HomePage(driver);
		homePage.enterTextInSearchField(testdataProp.getProperty("invalidProductName"));
		productPage = homePage.clickSearchButton();

//		ProductPage productPage = new ProductPage(driver);
		String actualMessage = productPage.invalidProductMessageText();
		String expectedMessage = testdataProp.getProperty("invalidProductMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "'Product not found' message not displayed");

	}

	@Test(priority = 3,dependsOnMethods={"verifySearchFieldWithValidProduct","verifySearchFieldWithInvalidProduct"})
	public void verifySearchFieldWithEmptyField() {
//		HomePage homePage = new HomePage(driver);
		productPage = homePage.clickSearchButton();

//		ProductPage productPage = new ProductPage(driver);
		String actualMessage = productPage.invalidProductMessageText();
		String expectedMessage = testdataProp.getProperty("invalidProductMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "'Product not found' message not displayed");

	}
}
