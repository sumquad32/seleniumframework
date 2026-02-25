package com.prac.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	final static int IMPLICIT_WAIT = 10;
	final static int PAGE_LOAD_TIME = 10;

	// Generate Time Stamp

	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	// Wait for the web element to load

	public static WebElement waitForElement(WebElement element, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;

	}

	// Take Screenshot

	public static String takeScreenshot(WebDriver driver, String testName) {

		String path = System.getProperty("user.dir") + "\\Screenshots\\" + testName + Utility.generateTimeStamp() + ".png";
		File dest = new File(path);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;

	}

}
