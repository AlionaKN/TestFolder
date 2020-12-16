package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AbstractPage {

	// links
		@FindBy(xpath = "//div[@class='caption']//a")
		private WebElement linkFirstItem;
		
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public String getTextFromLinkFirstItem() {
		return linkFirstItem.getText();
	}
}
