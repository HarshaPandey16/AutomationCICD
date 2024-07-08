package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button")
	List<WebElement> options;
	
	By optionName = By.cssSelector("span");
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void selectCountry(String countryName) {
		country.sendKeys("Ind");
		WebElement expectedOption = options.stream()
				.filter(option -> option.findElement(optionName).getText().equalsIgnoreCase(countryName))
				.findFirst().orElse(null);
		expectedOption.click();
	}
	
	public ConfirmationPage placeOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(submit).click().perform();
		return new ConfirmationPage(driver);
	}
	
	

}
