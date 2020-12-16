package asserts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestJunit {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.opencart.com/");
	}

	@After
	public void tearDown()  {
		driver.quit();
	}

	@Test
	public void test() {
		driver.findElement(By.id("wishlist-total")).click();
        //TODO: (1) Change it to AssertSame(JUnit)
		assertSame("Number of Search Box/es is not 1", driver.findElements(By.id("search")).size(), 1);

        String listElement =  driver.findElement(By.id("wishlist-total")).getText();
        //TODO: (2) Change it to AssertEquals (JUnit)
        assertEquals("Element name is not as expected: ", listElement, "Wish List (0)");

        WebElement passwordElement = driver.findElement(By.id("input-password"));
        String password = "testing123";
        passwordElement.sendKeys(password);
        //TODO: (3) Change it to AssertNotNull (JUnit)
        assertNotNull("Password field text is null", passwordElement.getText());
		
        boolean cartElement = driver.findElement(By.id("cart")).isDisplayed();
		//TODO: (4) Change it to AssertTrue (JUnit)
        assertTrue("Cart button is not displayed " , cartElement);
		
        driver.findElement(By.linkText("Register")).click();
        boolean agreeElement = driver.findElement(By.name("agree")).isSelected();
        //TODO: (5) Change it to AssertFalse (JUnit)
        assertFalse("Agree checkbox is selected: ", agreeElement);
	}

}
