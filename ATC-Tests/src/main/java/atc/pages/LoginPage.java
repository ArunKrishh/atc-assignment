package atc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atc.utils.ATCUtils;

public class LoginPage {	
	WebDriver driver;
	
	@FindBy(id="email")
	WebElement signInEmailTxtField;
	
	@FindBy(id="passwd")
	WebElement signInPasswordTxtField;
	
	@FindBy(id="SubmitLogin")
	WebElement signInBtn;
	
	ATCUtils utils;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		utils= new ATCUtils(driver);
	}
	
	public MyAccountPage signInToTheApplication(String userName, String password) {
		utils.waitForElementsToBeDisplayed(signInEmailTxtField);
		signInEmailTxtField.clear();
		signInEmailTxtField.sendKeys(userName);
		signInPasswordTxtField.clear();
		signInPasswordTxtField.sendKeys(password);
		signInBtn.click();
		
		return new MyAccountPage(driver);
	}
}
