package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegSuccessPage {

	WebDriver driver;
	
	@FindBy(xpath="//a[text()='Continue']")
	private WebElement continueButton;
	
	public AccountRegSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
}
