package utils;

import cucumber.deps.com.thoughtworks.xstream.mapper.Mapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class readFromExel {
    public static void main(String[] args) throws IOException {

        String excelFilePath="src/test/resources/Objects/Order_SKU.xlsx";
        FileInputStream inputstream=new FileInputStream(excelFilePath);

        XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
        XSSFSheet sheet=workbook.getSheetAt(0);	//XSSFSheet sheet=workbook.getSheet("Sheet1");
//
        ///////// Iterator ////////////////////////

        for (Row cells : sheet) {
            XSSFRow row = (XSSFRow) cells;

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();

                if ((cell.getCellType() == CellType.STRING) && (cell.getCellType() != null )) {
                    System.out.println(cell.getStringCellValue());
                }
            }
        }
    }
}
