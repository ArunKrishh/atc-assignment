package atc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import atc.utils.ATCUtils;

public class ProductDetailsPage {
	WebDriver driver;
	ATCUtils utils;
	
	
	@FindBy(css="input[name='qty']")
	WebElement quantityTextField;
	
	@FindBy(css="#uniform-group_1 select")
	WebElement sizeDDL;
	
	@FindBy(css="#color_to_pick_list li")
	List <WebElement> colorGrids;
	
	@FindBy(id="add_to_cart")
	WebElement addToCartBtn;
	
	@FindBy(css="span[title='Continue shopping']")
	WebElement continueShoppingBtn;
	
	@FindBy(css="a[title='Proceed to checkout']")
	WebElement proceedToCheckOutBtn;
	
	@FindBy(css=".breadcrumb.clearfix a[title='Summer Dresses']")
	WebElement summerDressesBreadCrumb;
	
	public ProductDetailsPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		utils = new ATCUtils(driver);
	}
	
	public void enterQuantity(String quantity) {
		quantityTextField.clear();
		quantityTextField.sendKeys(quantity);
	}
	
	public void selectSize(String size) {
		Select sizeSelectElement = new Select(sizeDDL);
		sizeSelectElement.selectByVisibleText(size);
	}
	
	public void clickColorGrid() {
		colorGrids.get(0).click();
	}
	
	public void clickAddToCartButton() {
		addToCartBtn.click();
	}
	
	public void clickContinueShoppingButton() {
		utils.waitForElementsToBeDisplayed(continueShoppingBtn);
		continueShoppingBtn.click();
	}
	
	public ShoppingCartPage clickProceedToCheckOutButton() {
		utils.waitForElementsToBeDisplayed(proceedToCheckOutBtn);
		proceedToCheckOutBtn.click();
		return new ShoppingCartPage(driver);
	}

	public ProductListPage clickSummerDressesBreadCrumb() {
		utils.waitForElementsToBeDisplayed(summerDressesBreadCrumb);
		summerDressesBreadCrumb.click();
		return new ProductListPage(driver);
	}
}
