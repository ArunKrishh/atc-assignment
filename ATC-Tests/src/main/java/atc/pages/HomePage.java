package atc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atc.utils.ATCUtils;

public class HomePage {
	WebDriver driver;
	public ATCUtils utils;
	
	
	@FindBy(css=".login")
	WebElement signInLink;
	
	@FindBy(css="#block_top_menu a[title='Women']")
	WebElement topNavBarWomen;
	
	@FindBy(xpath="/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/ul/li[2]/ul/li[3]/a")
	WebElement summerDressesUnderWomen;
	
	@FindBy(className="shopping_cart")
	WebElement cartDDL;
	
	@FindBy(id="button_order_cart")
	WebElement checkOutOnCartDDL;
	
	@FindBy(css=".account")
	WebElement userProfileBtn;
	
	@FindBy(css=".logout")
	WebElement signOut;
	
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		utils = new ATCUtils(driver);
	}
	
	public LoginPage clickSignInLink() {
		signInLink.click();
		return new LoginPage(driver);
	}
	
	public void mouseHoverWomenInTopNavBar() {
		Actions actions= new Actions(driver);
		actions.moveToElement(topNavBarWomen).build().perform();
	}
	
	public ProductListPage clickSummerDressessUnderWomen() {
		utils.waitForElementsToBeDisplayed(summerDressesUnderWomen);
		summerDressesUnderWomen.click();
		return new ProductListPage(driver);
	}
	
	public void mouseHoverOnCartDDL() {
		utils.waitForElementsToBeDisplayed(cartDDL);
		Actions actions= new Actions(driver);
		actions.moveToElement(cartDDL).build().perform();
	}

	public ShoppingCartPage clickCheckoutBtnUnderCartDDL() {
		checkOutOnCartDDL.click();
		return new ShoppingCartPage(driver);
	}
	
	public MyAccountPage clickUserProfileIcon() {
		userProfileBtn.click();
		return new MyAccountPage(driver);
	}
	
	public void clickSignOutLink() {
		signOut.click();
	}
}

