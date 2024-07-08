package framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderPurchase {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		String country = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		//LandingPage landingpage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("pihu16@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pihu@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> options = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement expectedOption = options.stream()
				.filter(option -> option.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(country))
				.findFirst().orElse(null);
		expectedOption.click();
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().perform();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();

	}

}
