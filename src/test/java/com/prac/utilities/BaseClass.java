package com.prac.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	private WebDriver driver = null;
	Properties prop = null;
	File configFile = null;
	FileInputStream fisConfig = null;

	public BaseClass() {

		String fileLoc = System.getProperty("user.dir") + "//Files//config.properties";
		prop = new Properties();
		configFile = new File(fileLoc);
		try {
			fisConfig = new FileInputStream(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fisConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public WebDriver startBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
