package framework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.TestComponents.BaseTest;
import framework.pageobjects.CartPage;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.OrdersPage;
import framework.pageobjects.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	String countryName = "India";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		ProductCatalouge productcatalouge = landingpage.loginApplication(input.get("email"), input.get("password"));
		//List<WebElement> products = productcatalouge.getProductsList();
		productcatalouge.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalouge.goToCartPage();
		Boolean match = cartpage.verifyCartProducts(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry(countryName);
		ConfirmationPage confirmationpage = checkoutpage.placeOrder();
		String confirmMessage = confirmationpage.orderConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		
		ProductCatalouge productcatalouge = landingpage.loginApplication("pihu16@gmail.com", "Pihu@1234");
		OrdersPage orderspage = productcatalouge.goToOrdersPage();
		Assert.assertTrue(orderspage.verifyOrdersProducts(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//framework//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData() 
	{
		
		return new Object[][] {{"pihu16@gmail.com","Pihu@1234","ZARA COAT 3"},{"ruhi16@gmail.com","Ruhi@1234","ADIDAS ORIGINAL"}};
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "pihu16@gmail.com");
		map.put("password", "Pihu@1234");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		map1.put("email", "ruhi16@gmail.com");
		map1.put("password", "Ruhi@1234");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map}, {map1}};
		
	}*/

}
