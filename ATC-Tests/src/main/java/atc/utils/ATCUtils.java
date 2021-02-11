package atc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ATCUtils {
	WebDriver driver;
	long timeOut = 30;
	WebDriverWait wait;
	String testDataFileLocation = "src\\test\\resources\\testData.properties";
	String screenShotfolderPath = "G:\\Project Stuffs\\CodeBase\\ATC-Tests\\test-output\\Screenshots\\";

	public ATCUtils(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, timeOut);
	}

	public void waitForElementsToBeDisplayed(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public Properties loadPropertyFile(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Properties testDataProperty = new Properties();
		testDataProperty.load(fis);
		return testDataProperty;
	}

	public HashMap<String, String> loadTestData() throws IOException {
		HashMap<String, String> testData = new HashMap<>();
		Properties testDataLoader = new Properties();
		testDataLoader = loadPropertyFile(testDataFileLocation);
		for (Map.Entry<Object, Object> entry : testDataLoader.entrySet()) {
			testData.put((String) entry.getKey(), (String) entry.getValue());
		}
		return testData;
	}

	public void takeScreenshot(String fileDesc) {
		try {
			TakesScreenshot takeScrennshot = (TakesScreenshot) driver;
			File screenshotSrcFile = takeScrennshot.getScreenshotAs(OutputType.FILE);
			File screenshotDestFile = new File(screenShotfolderPath + fileDesc+".png");
			FileUtils.copyFile(screenshotSrcFile, screenshotDestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
