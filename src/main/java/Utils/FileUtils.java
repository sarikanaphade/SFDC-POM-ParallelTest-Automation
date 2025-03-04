package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Constants.FileConstant;

public class FileUtils {
	
	static Properties prop = new Properties();
	public static Properties readfile(){
		String path = System.getProperty("user.dir")+"/src/main/java/InputData/credentials.properties";
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String readLoginPropertiesFile(String key){
		String path = FileConstant.LOGIN_PAGE_TEST_DATA_PATH;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	public static String readOppotunitiesPropertiesFile(String key){
		String path = FileConstant.OPPORTUNITIES_PAGE_TEST_DATA_PATH;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	public static String readLeadsPropertiesFile(String key){
		String path = FileConstant.LEADS_PAGE_TEST_DATA_PATH;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	public static String readContactsPropertiesFile(String key){
		String path = FileConstant.CONTACTS_PAGE_TEST_DATA_PATH;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public static String readHomePropertiesFile(String key){
		String path = FileConstant.HOME_PAGE_TEST_DATA_PATH;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
