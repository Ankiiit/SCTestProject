package steps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CommonSteps {

	public static  WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Before()
	public void setup() throws InterruptedException {
		Thread.sleep(2000);
		if(driver==null) {
		System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
	}

	@After()
	public void tearDown(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed()) {
			final byte[] scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(scr, "image/png", scenario.getName());
		}

		Thread.sleep(3000);
		System.out.println(scenario.getName());
		if(scenario.getName().equals("Voting for car")) {
			driver.quit();
		}
	}
	public static void createReponseFile(String str) {
		try {
			File myObj = new File("response.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("Clearing the existing file and writing new response");
				myObj.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("response.txt");
			myWriter.write(str);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
}
