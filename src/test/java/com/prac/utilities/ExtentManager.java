package com.prac.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent = null;
	private static String filePath = null;

	public static ExtentReports generateReport() {

		if (extent == null) {

			extent = new ExtentReports();
			filePath = System.getProperty("user.dir") + "\\Reports\\" + "Extent_Report+" + Utility.generateTimeStamp()
					+ ".html";
			File file = new File(filePath);

			ExtentSparkReporter spark = new ExtentSparkReporter(file);

			spark.config().setDocumentTitle("Prac Report");
			spark.config().setReportName("Prac Test Automation Result");
			spark.config().setTimeStampFormat("dd/MM/yy hh:mm:ss");

			extent.attachReporter(spark);

			Properties prop = new Properties();
			File file1 = new File("./Files/config.properties");
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(file1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			extent.setSystemInfo("Application URL", prop.getProperty("url"));
			extent.setSystemInfo("BrowserName", prop.getProperty("browser"));

		}

		return extent;

	}

}
