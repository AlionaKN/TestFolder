package testdata;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import basetest.BaseTest;
import page.MainPage;
import page.SearchPage;
import utilities.FileReaderUtils;

@RunWith(Parameterized.class)

public class TestParameterizedData extends BaseTest {

	private MainPage mainPage = new MainPage(driver);
	private SearchPage searchPage = new SearchPage(driver);
	private String productName;

	public TestParameterizedData(String productName) {
		super();
		this.productName = productName;
	}

	@Test
	public void testTestParameterizedData() {
		mainPage.writeInputSearch(productName);
		mainPage.clickButtonSearch();

		String actualResult = searchPage.getTextFromLinkFirstItem();

		assertEquals(productName, actualResult);
	}

	@Parameters
	public static Collection<String> parameters() throws IOException {
		return FileReaderUtils.getTestData("src/test/resources/TestData_3items.txt");
	}

//	@Parameters
//	public static Collection<String> parameters() {
//		return Arrays.asList("iPhone", "MacBook", "Canon EOS 5D");
//	}
}
