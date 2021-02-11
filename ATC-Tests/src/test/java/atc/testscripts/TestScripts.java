package atc.testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import atc.pages.HomePage;
import atc.pages.LoginPage;
import atc.pages.MyAccountPage;
import atc.pages.ProductDetailsPage;
import atc.pages.ProductListPage;
import atc.pages.ShoppingCartPage;
import atc.utils.ATCUtils;
import atc.utils.TestBaseClass;

public class TestScripts extends TestBaseClass {

	@Test
	public void testScript001() throws IOException {
		try {
			//Fetching Test Data from Property file
			ATCUtils utils = new ATCUtils(driver);
			HashMap<String, String> testData = utils.loadTestData();
			String firstName = testData.get("FirstName");
			String lastName = testData.get("LastName");
			String company = testData.get("Company");
			String address = testData.get("Adress");
			String addressLine2 = testData.get("AddressLine2");
			String city = testData.get("City");
			String state = testData.get("State");
			String zipCode = testData.get("ZipCode");
			String country = testData.get("Country");
			String homePhone = testData.get("HomePhone");
			String mobilePhone = testData.get("MobilePhone");
			String additionalInformation = testData.get("AdditionalInformation");
			String addressTitle = testData.get("AddressTitle");
			String emailId = testData.get("Emailid");
			String password = testData.get("Password");
			String quantity = testData.get("Quantity");
			String size = testData.get("Size");
			//1. Login into the application
			HomePage homePage = new HomePage(driver);
			LoginPage loginPage = homePage.clickSignInLink();
			MyAccountPage myAccountPage = loginPage.signInToTheApplication(emailId, password);
			//2. Navigate to 'My Addresses' and add a new address and Fill all the details then save
			myAccountPage.clickAddressesTab();
			//Cleanup
			myAccountPage.deleteExistingAddress();
			myAccountPage.clickAddNewAddressButton();
			myAccountPage.fillAndSubmitAddressForm(firstName, lastName, company, address, addressLine2, city, state,
					zipCode, country, homePhone, mobilePhone, additionalInformation, addressTitle);
			homePage = new HomePage(driver);
			//5. Navigate to 'Women' --> Summer dresses
			homePage.mouseHoverWomenInTopNavBar();
			ProductListPage productListPage = homePage.clickSummerDressessUnderWomen();
			//6. The Items would be in 'Grid view'. Change it to 'List View'.
			productListPage.clickListView();
			//7. Click on the first item to view.
			//8. Increase the quantity to 5
			//9. Change the size to 'L'
			//10.Select any colour. 
			//11.Add to cart
			//12.Click 'Continue shopping' and repeat the same for the other 2 items as well under the summer dresses.
			ShoppingCartPage shoppingCartPage;
			for (int iter = 0; iter < 3; iter++) {
				ProductDetailsPage productDetailsPage = productListPage.clickOnNthProductDisplyaedInList(iter);
				productDetailsPage.enterQuantity(quantity);
				productDetailsPage.clickColorGrid();
				productDetailsPage.selectSize(size);
				productDetailsPage.clickAddToCartButton();
				if (iter < 2) {
					productDetailsPage.clickContinueShoppingButton();
					productListPage = productDetailsPage.clickSummerDressesBreadCrumb();
				} else {
					shoppingCartPage = productDetailsPage.clickProceedToCheckOutButton();
				}
			}
			//13. Proceed to checkout and complete the payment
			shoppingCartPage = new ShoppingCartPage(driver);
			shoppingCartPage.clickProceedToCheckOutBtnFromOrderSummary();
			shoppingCartPage.clickProceedToCheckOutBtnFromAddress();
			shoppingCartPage.checkTermsOfServiceCheckBox();
			shoppingCartPage.clickProceedToCheckOutBtnFromShipping();
			shoppingCartPage.clickPayByCheckButton();
			shoppingCartPage.clickConfirmOrderBtn();
			String orderReferenceId = shoppingCartPage.getOrderReferenceId();
			homePage = new HomePage(driver);
			//14. Move to your profile and check 'order history and details'
			myAccountPage = homePage.clickUserProfileIcon();
			myAccountPage.clickOrderHistoryTab();
			Assert.assertTrue(myAccountPage.verifyOrderReferenceIdExistInOrderHistory(orderReferenceId),
					"Test Failed. Order Reference Id is not displayed in Order History");
			//15. Capture screenshot of the order history
			homePage.utils.takeScreenshot(new Object() {}.getClass().getEnclosingMethod().getName());
			//16. Sign out from the application
			homePage.clickSignOutLink();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
