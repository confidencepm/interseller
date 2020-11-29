package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import za.co.dinoko.assignment.confidencemukwindidza.constants.ExcelSheetNames;
import za.co.dinoko.assignment.confidencemukwindidza.constants.PlanetConstants;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesContants;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2020-11-29
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 * <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Component
public class SupportDataFileProcessor {

    private static final Logger log = LoggerFactory.getLogger(SupportDataFileProcessor.class);

    @Value("classpath:files/SupportData-V1.xlsx")
    private Resource supportExcelFileResource;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private List<Planet> planetList = new ArrayList<>();
    private List<Routes> routeList = new ArrayList<>();
    private DataFormatter dataFormatter = new DataFormatter();


    /**
     * What happens when immediately after the class has been instantiated / constructed
     * @throws Exception
     */
    @PostConstruct
    public void initBean() throws Exception {

        // Read the file into apache's POI's Workbook
        Workbook workbook = WorkbookFactory.create( supportExcelFileResource.getFile());
        log.info("Reading Excel File : {} which has {} sheets in total",
                supportExcelFileResource.getFilename(), workbook.getNumberOfSheets());

        extractPlanets( workbook);
        extractRoutes( workbook);
        loadDatabaseWithData();
    }

    private void loadDatabaseWithData() {
        log.info("Saving the data from file : {} to the database", supportExcelFileResource.getFilename());
        planetRepository.saveAll( getPlanetList());
        routeRepository.saveAll( getRouteList());
        log.info("Content now saved to the database!");
    }


    public List<Planet> getPlanetList() {
        return planetList;
    }

    public List<Routes> getRouteList() {
        return routeList;
    }

    public Planet getPlanetByNodeFromList(String planetNodeArg) {
        Optional<Planet> foundPlanet = planetList.stream()
                .filter(planet -> planet.getPlanetNode().equals(planetNodeArg)).findFirst();

        if ( foundPlanet.isPresent())
            return foundPlanet.get();

        return null;
    }


    /**
     * Extracts out the content of the file into a list.
     * This list will be the list of Planet Entites.
     * @param workbook
     * @return
     */
    private List<Planet> extractPlanets( Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetNames.PLANET_NAMES);
        Sheet planetNamesSheet = workbook.getSheet( ExcelSheetNames.PLANET_NAMES);

        // Looping through the ROWS of the Sheet.
        planetNamesSheet.forEach( row -> {
            Planet planet = new Planet();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != 0) {

                // Now look through each row's CELL
                row.forEach( cell -> {
                    int columnIndex = cell.getColumnIndex();

                    if ( PlanetConstants.EXCEL_COLUMN_PLANET_NODE == columnIndex)
                        planet.setPlanetNode( dataFormatter.formatCellValue(cell).trim());

                    if ( PlanetConstants.EXCEL_COLUMN_PLANET_NAME == columnIndex)
                        planet.setPlanetName( dataFormatter.formatCellValue(cell).trim());
                });

                planetList.add( planet);
            }
        });

        // If we don't have records then something really went wrong with readinf the excel file.
        if ( CollectionUtils.isEmpty( planetList)) {
            throw new RuntimeException("extractPlanets() - No records found while reading the Excel file");
        }

        log.info("Extracted a total of {} PLANETS from the excel file", planetList.size());
        return planetList;
    }


    private List<Routes> extractRoutes(Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetNames.ROUTES);
        Sheet routesSheet = workbook.getSheet( ExcelSheetNames.ROUTES);

        // Looping through the ROWS of the Sheet.
        routesSheet.forEach( row -> {
            Routes route = new Routes();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != 0) {

                // Now look through each row's CELL
                row.forEach( cell -> {
                    int columnIndex = cell.getColumnIndex();

                    if ( RoutesContants.EXCEL_COLUMN_ROUTE_ID == columnIndex)
                        route.setRouteId( Integer.parseInt( dataFormatter.formatCellValue(cell).trim()));

                    if ( RoutesContants.EXCEL_COLUMN_PLANET_ORIGIN == columnIndex) {
                        String planetOriginKey = dataFormatter.formatCellValue(cell);
                        Planet planetOrigin = getPlanetByNodeFromList( planetOriginKey);
                        route.setPlanetOrigin( planetOrigin);
                    }

                    if ( RoutesContants.EXCEL_COLUMN_PLANET_DESTINATION == columnIndex) {
                        String planetDestinationKey = dataFormatter.formatCellValue(cell);
                        Planet planetDestination = getPlanetByNodeFromList( planetDestinationKey);
                        route.setPlanetDestination( planetDestination);
                    }

                    if ( RoutesContants.EXCEL_COLUMN_PLANET_DISTANCE == columnIndex)
                        route.setDistanceInLightYears( Double.parseDouble( dataFormatter.formatCellValue(cell).trim()));

                });

                routeList.add( route);
            }
        });

        // If we don't have records then something really went wrong with readinf the excel file.
        if ( CollectionUtils.isEmpty( routeList)) {
            throw new RuntimeException("extractRoutes() - No records found while reading the Excel file");
        }

        log.info("Extracted a total of {} ROUTES from the excel file", routeList.size());
        return routeList;
    }
}
