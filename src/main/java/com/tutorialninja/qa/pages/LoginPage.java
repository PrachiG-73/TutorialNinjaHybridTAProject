package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailTextField;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTextField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(linkText= "Edit your account information")
	private WebElement myAccountLinkText;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailWarningMessage;
	
	public LoginPage(WebDriver driver) {		
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	//method
	public void enterEmailId(String emailId) {
		emailTextField.sendKeys(emailId);
	}
	
	public void enterPassword(String password ) {
		passwordTextField.sendKeys(password);
	}
	
	public AccountPage clickLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String getEmailWarningMessageText() {
		return emailWarningMessage.getText();
	}
}
