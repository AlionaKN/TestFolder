package testdata;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import basetest.BaseTest;
import page.MainPage;
import page.SearchPage;
import utilities.FileReaderUtils;

public class TestTxtReader extends BaseTest {

	private MainPage mainPage = new MainPage(driver);
	private SearchPage searchPage = new SearchPage(driver);
	
	@Test
	public void test() throws IOException {
		List <String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_iPhone.txt");
		
		String productName = testdata.get(0);
	
		mainPage.writeInputSearch(productName);
		mainPage.clickButtonSearch();
		
		String actualResult = searchPage.getTextFromLinkFirstItem();
		
		assertEquals("Actual result does not match expected. ", productName, actualResult);
	}
	
	
}
