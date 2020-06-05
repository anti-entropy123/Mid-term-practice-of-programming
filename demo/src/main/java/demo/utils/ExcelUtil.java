package demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* 依赖包
 * <dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi</artifactId>
		<version>RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi-ooxml</artifactId>
		<version>RELEASE</version>
	</dependency>
 */
public class ExcelUtil {
	
	public List readExcelFile(InputStream inputStream, String fileName){	
		Workbook workbook = null;
        try {
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (workbook == null) {
            return null;
        }
          
        int numOfSheet = workbook.getNumberOfSheets();
        List sheets = new ArrayList();
        for (int i = 0; i < numOfSheet; i++) {
        	Sheet sheet = workbook.getSheetAt(i);
            if(sheet == null) continue;
            
            int lastRowNum = sheet.getLastRowNum();
            if(lastRowNum == 0) continue;
            List rows = new ArrayList();
            for (int j  = 1; j <= lastRowNum; j++) {
            	Row row = sheet.getRow(j);
            	if(row == null) {
            		continue;
            	}
            	
            	
            	short lastCellNum = row.getLastCellNum();
            	IdAndState idAndState = new IdAndState();
            	for (int k = 0; k <= lastCellNum; k++) {
            		Cell cell = row.getCell(k);
            		if(cell == null) {
            			continue;
            		}
            		if (k == 0) {
            			idAndState.setId((int)cell.getNumericCellValue());
            		} else {
            			idAndState.setState(cell.getStringCellValue());
            		}
            	}
            	rows.add(idAndState);
            }
            sheets.add(rows);
        }
		return sheets;
	}
	
//	public static void main(String[] args) {
//		File file = new File("E:/record.xlsx");
//		try {
//			List sheets = readExcelFile(new FileInputStream(file), "record.xlsx");
//			System.out.println(sheets.size());
//			List rows = (List)sheets.get(0);
//			List cells = (List)rows.get(0);
//			Iterator it = cells.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
