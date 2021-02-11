package atc.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

import atc.utils.ATCUtils;

public class MyAccountPage {
	WebDriver driver;
	ATCUtils utils;

	@FindBy(css = ".myaccount-link-list a[title='Addresses']")
	WebElement addressTab;

	@FindBy(css = ".myaccount-link-list a[title='Orders']")
	WebElement orderHistoryTab;

	@FindBy(css = "a[title='Add an address']")
	WebElement addNewAdressBtn;

	@FindBy(id = "firstname")
	WebElement firstNameTxtField;

	@FindBy(id = "lastname")
	WebElement lastNameTxtField;

	@FindBy(id = "company")
	WebElement companyTxtField;

	@FindBy(id = "address1")
	WebElement addressLine1TxtField;

	@FindBy(id = "address2")
	WebElement addressLine2TxtField;

	@FindBy(id = "city")
	WebElement cityTxtField;

	@FindBy(id = "id_state")
	WebElement stateDDL;

	@FindBy(id = "postcode")
	WebElement postcodeTxtField;

	@FindBy(id = "id_country")
	WebElement countryDDL;

	@FindBy(id = "phone")
	WebElement phoneNumberTxtField;

	@FindBy(id = "phone_mobile")
	WebElement mobileNumberTxtField;

	@FindBy(id = "other")
	WebElement additionalInformationTxtField;

	@FindBy(id = "alias")
	WebElement addressTitleTxtField;

	@FindBy(id = "submitAddress")
	WebElement saveButton;

	@FindBy(css = ".address_update a[title='Delete']")
	WebElement deleteButton;

	@FindBy(css = "#order-list tr td .color-myaccount")
	List<WebElement> orderReferenceIdColumnInOrderHistory;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utils = new ATCUtils(driver);
	}

	public void clickAddressesTab() {
		addressTab.click();
	}

	public void clickOrderHistoryTab() {
		utils.waitForElementsToBeDisplayed(orderHistoryTab);
		orderHistoryTab.click();
	}

	public void clickAddNewAddressButton() {
		utils.waitForElementsToBeDisplayed(addNewAdressBtn);
		addNewAdressBtn.click();
	}

	public void deleteExistingAddress() {
		try {
			if (deleteButton.isEnabled()) {
				deleteButton.click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		} catch (NoSuchElementException e) {
			// No Existing Addres Available
		}

	}

	public void fillAndSubmitAddressForm(String firstName, String lastName, String company, String addressLine1,
			String addressLine2, String city, String state, String zipCode, String country, String homePhone,
			String mobilePhone, String additionalInfo, String addressTitle) {
		firstNameTxtField.sendKeys(firstName);
		lastNameTxtField.sendKeys(lastName);
		companyTxtField.sendKeys(company);
		addressLine1TxtField.sendKeys(addressLine1);
		addressLine2TxtField.sendKeys(addressLine2);
		cityTxtField.sendKeys(city);
		postcodeTxtField.sendKeys(zipCode);
		phoneNumberTxtField.sendKeys(homePhone);
		mobileNumberTxtField.sendKeys(mobilePhone);
		additionalInformationTxtField.sendKeys(additionalInfo);
		addressTitleTxtField.clear();
		addressTitleTxtField.sendKeys(addressTitle);
		Select stateSelectElement = new Select(stateDDL);
		stateSelectElement.selectByValue(state);
		saveButton.click();
	}

	public boolean verifyOrderReferenceIdExistInOrderHistory(String orderReferenceId) {
		boolean result = false;
		PageFactory.initElements(driver, this);
		if (!orderReferenceIdColumnInOrderHistory.isEmpty()) {
			for (WebElement element : orderReferenceIdColumnInOrderHistory) {
				if(element.getText().equals(orderReferenceId)) {
					return true;
				}
			}
		} 
		return result;
	}
}
