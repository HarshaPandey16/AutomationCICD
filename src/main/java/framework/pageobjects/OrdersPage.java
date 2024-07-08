package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersProducts;
	
	public Boolean verifyOrdersProducts(String productName) {
		Boolean match = ordersProducts.stream()
				.anyMatch(ordersProduct -> ordersProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
