package waits;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basetest.BaseTest;
import page.MainPage;
import utilities.WaitUtils;

public class TestCart extends BaseTest{

	private MainPage mainPage = new MainPage(driver);
	
	@Test
	public void testCart() {
		
		String text = "%s item(s)";
		mainPage.clickLinkDesktops();
		mainPage.clickLinkAllDesktops();
		
		addToCart("iPhone","MacBook","Sony VAIO");
		
		String actualCartText = mainPage.buttonCart.getText();
		String expectedCartText = String.format(text, 3);
		assertTrue("Chart does not contain expected text", actualCartText.contains(expectedCartText));
	
		mainPage.getButtonsRemoveItem().forEach(button -> mainPage.removeItemFromCart());
	
		actualCartText = mainPage.buttonCart.getText();
		expectedCartText = String.format(text, 0);
		assertTrue("Chart does not contain expected text", actualCartText.contains(expectedCartText));
	}

	private void addToCart(String...productNames) {
		for (String name : productNames) {
			mainPage.clickAddToCart(name);
		}
	}
	
}
