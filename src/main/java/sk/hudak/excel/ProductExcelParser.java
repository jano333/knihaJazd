package sk.hudak.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hudak on 09.08.2016.
 */
public class ProductExcelParser {

    public static void main(String[] args) {
        ProductExcelParser parser = new ProductExcelParser();
        ExcelProductInportResult result = parser.parseExcel("");
    }

    private ExcelProductInportResult parseExcel(String pathToExcelFile) {
        Workbook sourceWorkbook = null;
        try {

//            sourceWorkbook = WorkbookFactory.create(sourceExcel);
//
//            Iterator<Row> rowIterator = sourceWorkbook.getSheetAt(0).iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//
//
//            }
            return null;

        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return null;
        }
    }
}