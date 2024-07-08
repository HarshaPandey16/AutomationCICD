package framework.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.TestComponents.BaseTest;
import framework.TestComponents.Retry;
import framework.pageobjects.CartPage;
import framework.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {

		landingpage.loginApplication("pihu16@gmail.com", "Pihu@12");
		landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		ProductCatalouge productcatalouge = landingpage.loginApplication("ruhi16@gmail.com", "Ruhi@1234");
		//List<WebElement> products = productcatalouge.getProductsList();
		productcatalouge.addProductToCart(productName);
		CartPage cartpage = productcatalouge.goToCartPage();
		Boolean match = cartpage.verifyCartProducts("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}
