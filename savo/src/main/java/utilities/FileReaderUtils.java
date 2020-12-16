package utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

import models.ShopItem;

public class FileReaderUtils {
	
	private static XStream xstream = new XStream();

	public static List<String> getTestData(String fileName) throws IOException {
		List<String> records = new ArrayList<String>();
		String record;
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {		
			while ((record = buffer.readLine()) != null) {
				records.add(record);
			}
		}		
		return records;
	}
	
	public static void writeShopItemToFile(ShopItem item, String fileName ) throws IOException {
		try {
			FileUtils.writeStringToFile(new File("src/test/resources/" + fileName + ".xml"), xstream.toXML(item));
		} catch (IOException e) {
			throw new IOException("Was unable to write file with name fileName", e.getCause());
		}
	}
	
	public static ShopItem readFileToShopItem(String fileName) throws IOException {
		try {
			return (ShopItem) xstream.fromXML(FileUtils.readFileToString(new File("src/test/resources/" + fileName + ".xml")));
		} catch (IOException e) {
			throw new IOException("Was unable to read from file with name fileName", e.getCause());
		}
	}
}