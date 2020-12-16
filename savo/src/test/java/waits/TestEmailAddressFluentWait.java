package waits;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.AssertionFailedError;

public class TestEmailAddressFluentWait {
	
	private WebDriver driver;
	private static String expectedText = "Warning: The E-Mail Address was not found in our records, please try again!";
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.opencart.com/index.php?route=account/forgotten");
	}

	@After
	public void tearDown()  {
		driver.quit();
	}

	@Test
	public void testEmailAddressText() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div[2]/input")).click();
		assertTrue("Error message is not as expected", verifyEmailText());
	}
		
		private Boolean verifyEmailText() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(50))
				.ignoring(NoSuchElementException.class)
				.withMessage("Waiting time has expired.Error");
		
		WebElement alertElement = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]"));
			}
			
		});

			return alertElement.getText().contains(expectedText);
	}
	
}
