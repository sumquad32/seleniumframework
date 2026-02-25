package com.prac.pages;

import java.awt.print.Pageable;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prac.utilities.Utility;

public class LoginPage {

	private WebDriver driver = null;
	private JavascriptExecutor jse = null;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.jse = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginBtn;

	public void enterEmail(String email) {

		Utility.waitForElement(emailField, driver);
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", emailField);
		emailField.sendKeys(email);

	}

	public void enterPassword(String password) {

		Utility.waitForElement(passwordField, driver);
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", passwordField);
		passwordField.sendKeys(password);

	}

	public void clickOnLoginButton() {

		Utility.waitForElement(loginBtn, driver);
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", loginBtn);
		loginBtn.click();

	}

}
