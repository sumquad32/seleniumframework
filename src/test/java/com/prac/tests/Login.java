package com.prac.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prac.pages.LoginPage;
import com.prac.utilities.BaseClass;
import com.prac.utilities.ReadExcel;

public class Login extends BaseClass {

	public WebDriver driver = null;
	LoginPage lp = null;

	@BeforeMethod
	public void setUp() {

		driver = startBrowser();
		lp = new LoginPage(driver);
	}

	@AfterMethod
	public void testDown() {

		if (driver != null) {
			driver.quit();
		}

	}

	@Test(priority = 1, groups = { "login", "smoke", "regression" })
	public void verifyLoginWithValidCredentials() {

		lp.enterEmail("email");
		lp.enterPassword("pass");
		lp.clickOnLoginButton();

		Assert.fail();

	}

	@Test(priority = 2, groups = { "login", "smoke", "regression" })
	public void verifyLoginWithInValidCredentials() {

		lp.enterEmail("email");
		lp.enterPassword("pass");
		lp.clickOnLoginButton();

	}

	@DataProvider(name = "logindata")
	public Object[][] testData() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\Files\\testdata.xlsx";
		return ReadExcel.readExcelData(filePath, "Sheet1");

	}

	@Test(priority = 3, dataProvider = "logindata")
	public void verifyMultipleLogins(String email, String pass) {

		lp.enterEmail(email);
		lp.enterPassword(pass);
		lp.clickOnLoginButton();

	}

}
