package za.co.dinoko.assignment.confidencemukwindidza.supportData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class SupportData {

    public static void readingFile() throws IOException {

        // Setting the path to excel file with planets data
        File file = new File("src/main/resources/files/SupportData-V1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet= xssfWorkbook.getSheetAt(0);

        Iterator<Row> rowIterator = xssfSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {

            Planet planet = new Planet();

            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            // Setting the planetNode from file
            Cell cell = cellIterator.next();
            planet.setPlanetNode(cell.toString());

            // Setting the planetName from file
            cell = cellIterator.next();
            planet.setPlanetName(cell.toString());

        }
        xssfWorkbook.close();
        fileInputStream.close();
    }
}
