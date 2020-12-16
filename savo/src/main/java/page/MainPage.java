package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtils;

public class MainPage extends AbstractPage{

	// links
	@FindBy(linkText = "Desktops")
	private WebElement linkDesktops;
	
	@FindBy(linkText = "Show All Desktops")
	private WebElement linkAllDesktops;
	
	//button
	@FindBy(id = "cart-total")
	public WebElement buttonCart;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement buttonSearch;
    
    //input
    @FindBy(name = "search")
    private WebElement inputSearch;
	
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLinkDesktops() {
		linkDesktops.click();
	}
	public void clickLinkAllDesktops() {
		linkAllDesktops.click();
	}
	
	public void clickAddToCart(String productName) {
		driver.findElement(By.xpath("//div[contains(@class, 'product-layout') and .//a[text()='"+ productName+"']]//button[.//span[text()='Add to Cart']]")).click();
		WaitUtils.waitForJS(driver);
	}
	
	private void openCart() {
		buttonCart.click();
	}
	
	public List<WebElement> getButtonsRemoveItem() {
		return driver.findElements(By.xpath("//button[@title='Remove']"));
	}
	
	public void removeItemFromCart() {
		openCart();
		getButtonsRemoveItem().get(0).click();
		WaitUtils.waitForJS(driver);
	}
	
	public void writeInputSearch(String itemName) {
        inputSearch.sendKeys(itemName);
    }
    public void clickButtonSearch() {
        buttonSearch.click();
    }
}
