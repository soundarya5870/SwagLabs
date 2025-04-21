package dataproviders;

import java.io.File;

import org.testng.annotations.DataProvider;
import utils.ExcelUtil;

public class DataProviders {

	@DataProvider(name = "loginData")
	public static Object[][] readExcelData() {
	    try {
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        File file = new File(classLoader.getResource("TestData/LoginData.xlsx").getFile());
	        return ExcelUtil.getTestData(file.getAbsolutePath(), "Sheet1");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new Object[][] {}; // return empty to avoid null pointer
	    }
	}
}

