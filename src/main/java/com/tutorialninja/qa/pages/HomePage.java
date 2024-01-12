package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountOption;
	
	
	@FindBy(xpath="//a[text()='Login'][1]")
	private WebElement loginOption;
	
	@FindBy(xpath="//a[text()='Register'][1]")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name ='search']")
	private WebElement searchField;
	
	@FindBy(xpath="//span[@class ='input-group-btn']/button[@type='button']")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//methods	
	public void clickOnMyAccount() {
		myAccountOption.click();
	}
	
	public LoginPage clickOnLogin() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegister() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterTextInSearchField(String productName) {
		searchField.sendKeys(productName);
	}
	
	public ProductPage clickSearchButton() {
		searchButton.click();
		return new ProductPage(driver);
	}
	
}
