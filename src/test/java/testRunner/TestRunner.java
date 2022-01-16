package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"features"},
		glue = {"steps"},
		plugin = {"pretty","json:target/json-report/cucumber.json"},
		strict = true,
		monochrome = true,
		//tags = {"@Smoke"},
		dryRun = false
		)
public class TestRunner {

}
