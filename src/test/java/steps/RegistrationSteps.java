package steps;

import static org.junit.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.annotations.findby.By;

public class RegistrationSteps {
	WebDriver driver;

	public RegistrationSteps(CommonSteps common_steps) {
		this.driver = common_steps.getDriver();
	}

	@Given("I am on Buggy cars rating homepage")
	public void i_am_on_Buggy_cars_rating_homepage() {
		driver.get("https://buggy.justtestit.org/overall");

	}

	@When("I click on Register link button")
	public void i_click_on_Register_link_button() {
		driver.findElement(By.xpath("//*[text()='Register']")).click();

	}

	@When("enter registration details")
	public void enter_registration_details() {
		int rand = ThreadLocalRandom.current().nextInt(1000, 100000);
		driver.findElement(By.id("username")).sendKeys("Test@" + rand);
		driver.findElement(By.id("firstName")).sendKeys("Test");
		driver.findElement(By.id("lastName")).sendKeys("scenario1");
		driver.findElement(By.id("password")).sendKeys("Xerox@2014");
		driver.findElement(By.id("confirmPassword")).sendKeys("Xerox@2014");

	}

	@Then("Message is Displayed “Registration is Successful”")
	public void message_is_Displayed_Registration_is_Successful() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[text()='Register'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		String text = driver.findElement(By.xpath("//*[contains(@class,'alert-success')]")).getText().trim();
		if (!(text.equals("Registration is successful"))) {
			fail("Registration Failed !!");
		}
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
	}

	@When("enter registration details with existing username")
	public void enter_registration_details_with_existing_username() {
		driver.findElement(By.id("username")).sendKeys("Test@10");
		driver.findElement(By.id("firstName")).sendKeys("Test");
		driver.findElement(By.id("lastName")).sendKeys("scenario1");
		driver.findElement(By.id("password")).sendKeys("Xerox@2014");
		driver.findElement(By.id("confirmPassword")).sendKeys("Xerox@2014");

	}

	@Then("Message is Displayed “User Exists”")
	public void message_is_Displayed_User_Exists() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[text()='Register'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		String text = driver.findElement(By.xpath("(//*[contains(@class,'alert-danger')])[7]")).getText().trim();
		if ((text.equals("UsernameExistsException: User already exists"))) {
			fail("Registration Failed !!");
		}
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
	}

	@When("I fire a get request using restAssured")
	public void i_fire_a_get_request_using_restAssured() {
		RestAssured.baseURI = "https://buggy.justtestit.org/";

	}

	@Then("I get http response as OK")
	public void i_get_http_response_as_OK() {

		Response response = RestAssured.get("https://buggy.justtestit.org/overall");
		CommonSteps.createReponseFile("Response details received are below:"+"\n"
		+"Response Status Code:" + response.getStatusCode()+"\n"
		+"Response Status Line: "+response.getStatusLine()+"\n"
		+"Response Time: "+response.getTime());
	}	

}
