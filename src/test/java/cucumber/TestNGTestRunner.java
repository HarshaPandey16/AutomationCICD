package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Cucumber-> To run cucumber feature file, it takes help from TestNG / JUnit
@CucumberOptions(features="src/test/java/cucumber",glue="framework.stepDefinitions",
monochrome = true, tags="@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	
}
