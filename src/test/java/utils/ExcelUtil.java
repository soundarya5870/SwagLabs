package utils;


import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	public static Object[][] getTestData(String filePath, String sheetName) {
		// TODO Auto-generated method stub
		Object[][] data = null;

		try (FileInputStream fis = new FileInputStream(filePath);
	             Workbook workbook = WorkbookFactory.create(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getLastCellNum();

	            data = new Object[rowCount - 1][colCount];

	            for (int i = 1; i < rowCount; i++) {
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    data[i - 1][j] = getCellValue(cell);
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return data;
	    }

	    private static Object getCellValue(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue();
	                } else {
	                    return cell.getNumericCellValue();
	                }
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case FORMULA:
	                return cell.getCellFormula();
	            case BLANK:
	                return "";
	            default:
	                return "";
	        }
	    }
	}