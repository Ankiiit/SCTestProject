package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.fail;

public class VotingSteps {

	WebDriver driver;

	public VotingSteps(CommonSteps common_steps) {
		this.driver = common_steps.getDriver();
	}

	@Given("I am on website homepage")
	public void i_am_on_website_homepage() {
		driver.get("https://buggy.justtestit.org/overall");

	}

	@When("I fill in login details to login")
	public void i_fill_in_login_details_to_login() throws InterruptedException {
		driver.findElement(By.name("login")).sendKeys("Test@97083");
		driver.findElement(By.name("password")).sendKeys("Xerox@2014");
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		Thread.sleep(3000);
	}

	@When("click on the first car to vote")
	public void click_on_the_first_car_to_vote() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(@title,'Diablo')]")).click();
		Thread.sleep(3000);
	}

	@Then("my vote gets counted")
	public void my_vote_gets_counted() {
		int size = driver.findElements(By.xpath("//*[text()='Thank you for your vote!']")).size();
		if (!(size > 0)) {
			fail("Vote failed");
		}

	}

}
