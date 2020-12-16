package testdata;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import basetest.BaseTest;
import models.ShopItem;
import page.MainPage;
import page.SearchPage;
import utilities.FileReaderUtils;

public class TextXMLReader extends BaseTest {

private MainPage mainPage = new MainPage(driver);
private SearchPage searchPage = new SearchPage(driver);

	@Test
	public void testXMLReader() throws IOException {
		
		ShopItem item = new ShopItem();
		item.setName("iPhone");
		item.setBrand("Apple");
		item.setPrice("$123.20");
		
		String fileName = "phone";
		
		FileReaderUtils.writeShopItemToFile(item, fileName);
		
		ShopItem iPhone = FileReaderUtils.readFileToShopItem(fileName);
		String expectedResult = iPhone.getName();
		
		mainPage.writeInputSearch(expectedResult);
		mainPage.clickButtonSearch();
		
		String actualResult = searchPage.getTextFromLinkFirstItem();
		
		assertEquals("Actual Result does not match. Actual result:" + actualResult + "but expected" + expectedResult, expectedResult, actualResult);
		
	}

}
