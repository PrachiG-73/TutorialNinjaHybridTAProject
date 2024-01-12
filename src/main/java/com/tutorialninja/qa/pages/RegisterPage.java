package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement privacyPolicyCheckboxField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(@id,'content')]")
	private WebElement accountCreationMessageText;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterRadioButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement WarningMessage;
	
	@FindBy(xpath="//input[@name = 'firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath="//input[@name = 'lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	
	@FindBy(xpath="//input[@name = 'email']/following-sibling::div")
	private WebElement emailWarningMessage;
	
	
	@FindBy(xpath="//input[@name = 'telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath="//input[@name = 'password']/following-sibling::div")
	private WebElement passwordWarningMessage;	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	public void enterTelephone(String telephoneNo) {
		telephoneField.sendKeys(telephoneNo);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}
	
	public void checkPrivacyPolicyCheckbox() {
		privacyPolicyCheckboxField.click();
	}
	
	public AccountRegSuccessPage clickContinueButton() {
		continueButton.click();
		return new AccountRegSuccessPage(driver);
	}
	
	public String accountCreationMessage() {
	return 	accountCreationMessageText.getText();
	}
	
	public void clickNewsletterRadioButton() {
		newsletterRadioButton.click();
	}
	
	public String getWarningMessage() {
		return WarningMessage.getText();
	}
	
	public String getFirstNameWarningMessage() {
		return firstNameWarningMessage.getText();
	}
	
	public String getLastNameWarningMessage() {
		return lastNameWarningMessage.getText();
	}
	public String getEmailWarningMessage() {
		return emailWarningMessage.getText();
	}
	public String getTelephoneWarningMessage() {
		return telephoneWarningMessage.getText();
	}
	public String getPasswordWarningMessage() {
		return passwordWarningMessage.getText();
	}	
	
}
