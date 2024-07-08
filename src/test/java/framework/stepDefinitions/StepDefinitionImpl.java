package framework.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import framework.TestComponents.BaseTest;
import framework.pageobjects.CartPage;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalouge productcatalouge;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Eccomerce Page")
	public void I_landed_on_Eccomerce_Page() throws IOException 
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) 
	{
		productcatalouge = landingPage.loginApplication(username,password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		productcatalouge.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and Submit the order$")
	public void checkout_and_submit_order(String productName)
	{
		cartpage = productcatalouge.goToCartPage();
		Boolean match = cartpage.verifyCartProducts(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationpage.orderConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String string) 
	{
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}

}
