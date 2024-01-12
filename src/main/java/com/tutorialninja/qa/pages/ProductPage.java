package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	
	//objects
	@FindBy(linkText="HP LP3065")
	private WebElement productLink;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidProductMessage;
	
	//constructor
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//methods
	public boolean checkIfProductIsDisplayed() {
	return productLink.isDisplayed();
	}
	
	public String invalidProductMessageText() {
		return invalidProductMessage.getText();
	}
}
