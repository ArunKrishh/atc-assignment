package atc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atc.utils.ATCUtils;

public class ShoppingCartPage {
	WebDriver driver;
	ATCUtils utils;

	@FindBy(css = ".cart_navigation.clearfix a[title='Proceed to checkout']")
	WebElement proceedToCheckOutBTNFromSummary;

	@FindBy(css = ".cart_navigation.clearfix [name='processAddress']")
	WebElement proceedToCheckOutBTNFromAddress;

	@FindBy(css = ".cart_navigation.clearfix [name='processCarrier']")
	WebElement proceedToCheckOutBTNFromShipping;

	@FindBy(css = ".checker [type='checkbox']")
	WebElement termOfServiceCheckBox;

	@FindBy(css = ".cheque")
	WebElement payByCheckButton;

	@FindBy(css = "#cart_navigation [type='Submit']")
	WebElement confirmOrderBtn;

	@FindBy(css = ".box.order-confirmation")
	WebElement orderConfirmationDetails;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utils = new ATCUtils(driver);
	}

	public void clickProceedToCheckOutBtnFromOrderSummary() {
		utils.waitForElementsToBeDisplayed(proceedToCheckOutBTNFromSummary);
		proceedToCheckOutBTNFromSummary.click();
		PageFactory.initElements(driver, this);
	}

	public void clickProceedToCheckOutBtnFromAddress() {
		utils.waitForElementsToBeDisplayed(proceedToCheckOutBTNFromAddress);
		proceedToCheckOutBTNFromAddress.click();
		PageFactory.initElements(driver, this);
	}

	public void checkTermsOfServiceCheckBox() {
		termOfServiceCheckBox.click();
	}

	public void clickProceedToCheckOutBtnFromShipping() {
		utils.waitForElementsToBeDisplayed(proceedToCheckOutBTNFromShipping);
		proceedToCheckOutBTNFromShipping.click();
		PageFactory.initElements(driver, this);
	}

	public void clickPayByCheckButton() {
		utils.waitForElementsToBeDisplayed(payByCheckButton);
		payByCheckButton.click();
	}

	public void clickConfirmOrderBtn() {
		utils.waitForElementsToBeDisplayed(confirmOrderBtn);
		confirmOrderBtn.click();
	}

	public String getOrderReferenceId() {
		utils.waitForElementsToBeDisplayed(orderConfirmationDetails);
		String orderReferenceid = orderConfirmationDetails.getText().split("order reference")[1].split("\\.")[0].trim();
		return orderReferenceid;
	}

}
