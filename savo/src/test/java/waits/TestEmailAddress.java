package waits;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestEmailAddress {
	
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
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(
				ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")), expectedText));
				
		
	}
	
}
