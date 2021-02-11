package atc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atc.utils.ATCUtils;

public class ProductListPage {
	WebDriver driver;
	
	
	@FindBy(id="list")
	WebElement listView;
	
	@FindBy(css=".product-container .product-name")
	List <WebElement> productList;
	
	
	public ProductListPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickListView() {
		listView.click();
	}
	
	public ProductDetailsPage clickOnNthProductDisplyaedInList(int productIndex) {
		PageFactory.initElements(driver, this);
		productList.get(productIndex).click();
		return new ProductDetailsPage(driver);
	}
}
