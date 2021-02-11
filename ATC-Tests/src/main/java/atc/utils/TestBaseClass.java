package atc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBaseClass {
 
	protected WebDriver driver;
	static String appUrl;
	String configFileLocation="src\\test\\resources\\config.properties";
	Properties configpropertyFileReader= new Properties();
	ATCUtils utils;
	

	@BeforeMethod
	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		utils= new ATCUtils(driver);
		configpropertyFileReader=utils.loadPropertyFile(configFileLocation);
		appUrl=(String) configpropertyFileReader.get("url");
		driver.manage().window().maximize();
		driver.get(appUrl);
		
	}
	
	
	
	@BeforeSuite(alwaysRun = true)
	void loadProperties() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\testData.properties");
		configpropertyFileReader.load(fis);
		appUrl=(String) configpropertyFileReader.get("url");
	}

	
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
 
}
