package asserts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHamcrest {
	
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
        //TODO: (1) Change it to compare size(Hamcrest)
        assertThat("Number of Search Box/es is not 1", driver.findElements(By.id("search")).size(), is(1));

        String listElement =  driver.findElement(By.id("wishlist-total")).getText();
        //TODO: (2) Change it to compare text (Hamcrest)
        assertThat( listElement, is(equalTo("Wish List (0)")));

        WebElement emailElement = driver.findElement(By.id("input-email"));
        String email = "testing123";
        emailElement.sendKeys(email);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        emailElement = driver.findElement(By.id("input-email"));
        //TODO: (3) Change it to use allOf( is(equalTo()) and containsString() ) (Hamcrest)
        assertThat("Password field text is not " , emailElement.getAttribute("value"), allOf(is(equalTo("testing123")), containsString("123")));
		
        boolean cartElement = driver.findElement(By.id("cart")).isDisplayed();
		//TODO: (4) Change it to compare bollean value (Hamcrest)
        assertThat(" Cart button is not displayed: " , cartElement, is(equalTo(true)));
        
        driver.findElement(By.linkText("Register")).click();
		
        boolean agreeElement = driver.findElement(By.name("agree")).isSelected();
        //TODO: (5) Change it to is(not()) (Hamcrest)
        assertThat("Agree checkbox is selected", agreeElement,is(not(equalTo(true))));

}
}