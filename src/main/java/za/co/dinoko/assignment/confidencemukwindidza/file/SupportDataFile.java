package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import za.co.dinoko.assignment.confidencemukwindidza.constants.ExcelSheetNames;
import za.co.dinoko.assignment.confidencemukwindidza.constants.PlanetContants;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2020-11-29
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 * <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Component
public class SupportDataFile {

    private static final Logger log = LoggerFactory.getLogger(SupportDataFile.class);

    @Value("classpath:files/SupportData-V1.xlsx")
    private Resource supportExcelFileResource;

    private List<Planet> planetList = new ArrayList<>();


    @PostConstruct
    public void initBean() throws Exception {
        Workbook workbook = WorkbookFactory.create( supportExcelFileResource.getFile());
        log.info("Reading Excel File : {} which has {} sheets in total",
                supportExcelFileResource.getFilename(), workbook.getNumberOfSheets());

        extractPlanets( workbook);
    }

    private List<Planet> extractPlanets( Workbook workbook) {
        log.info("Processing the sheet \"{}\"", ExcelSheetNames.PLANET_NAMES);
        DataFormatter dataFormatter = new DataFormatter();
        Sheet planetNamesSheet = workbook.getSheet( ExcelSheetNames.PLANET_NAMES);

        planetNamesSheet.forEach( row -> {
            Planet planet = new Planet();

            row.forEach( cell -> {
                int columnIndex = cell.getColumnIndex();

                if (PlanetContants.EXCEL_COLUMN_PLANET_NODE == columnIndex)
                    planet.setPlanetNode( dataFormatter.formatCellValue(cell).trim());

                if (PlanetContants.EXCEL_COLUMN_PLANET_NAME == columnIndex)
                    planet.setPlanetName( dataFormatter.formatCellValue(cell).trim());
            });

            planetList.add( planet);
        });

        if ( CollectionUtils.isEmpty( planetList)) {
            throw new RuntimeException("extractPlanets() - No records found while reading the Excel file");
        }

        log.info("Extracted a total of {} records from the excel file", planetList.size());
        return planetList;
    }


    public List<Planet> getPlanetList() {
        return planetList;
    }
}
