package com.corendon.luggage_finder.excel;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.JDBC;
import com.corendon.luggage_finder.model.Luggage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains the logic to import and export Excel sheets.
 *
 * @author Tom J. Wassing
 */
public final class Excel {

    private static final int COLUMN_REGISTRATION_NUMBER = 0;
    private static final int COLUMN_DATE_FOUND = 1;
    private static final int COLUMN_TIME_FOUND = 2;
    private static final int COLUMN_LUGGAGE_TYPE = 3;
    private static final int COLUMN_BRAND = 4;
    private static final int COLUMN_FLIGHT_NUMBER = 5;
    private static final int COLUMN_LUGGAGE_TAG = 6;
    private static final int COLUMN_LOCATION_FOUND = 7;
    private static final int COLUMN_MAIN_COLOR = 8;
    private static final int COLUMN_SECOND_COLOR = 9;
    private static final int COLUMN_SIZE = 10;
    private static final int COLUMN_WEIGHT = 11;
    private static final int COLUMN_PASSENGER_AND_CITY = 12;
    private static final int COLUMN_OTHER_CHARACTERISTICS = 13;

    /**
     * Reads a .xlsx file and outputs a lists of {@link Luggage}s.
     *
     * @param file The excel file.
     * @return If the insert succeeded
     */
    public static boolean fromExcel(File file) throws IOException, ParseException {
        // reading excel file and getting first sheet
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);

        String query =
                "SET @flight_number = ?;\n" +
                "SET @luggage_tag = ?;\n" +
                "SET @last_name = ?;\n" +
                "SET @city = ?;\n" +

                "INSERT IGNORE INTO flights (flightnumber) VALUES (@flight_number);\n" +
                "INSERT IGNORE INTO labels (tag, flight_id) VALUES (@luggage_tag, (SELECT id FROM flights WHERE flightnumber = @flight_number LIMIT 1));\n" +
                "INSERT IGNORE INTO passengers (last_name, city) VALUES (@last_name, @city);\n" +

                "INSERT INTO luggages (\n" +
                "registration_id,\n" +
                "date_found, \n" +
                "luggage_type_id,\n" +
                "brand_id,\n" +
                "label_id,\n" +
                "location_found_id,\n" +
                "primary_color_id,\n" +
                "secondary_color_id,\n" +
                "size_width,\n" +
                "size_length,\n" +
                "size_height,\n" +
                "weight,\n" +
                "passenger_id,\n" +
                "other_characteristics) VALUES(" +
                "?,\n" + // registration_id
                "?,\n" + // date_found
                "(SELECT id FROM luggage_types WHERE name = ? LIMIT 1),\n" + // luggage type
                "(SELECT id FROM brands WHERE name = ? LIMIT 1),\n" + // brand
                "(SELECT id FROM labels WHERE tag = @luggage_tag LIMIT 1),\n" + // label
                "(SELECT id FROM airports WHERE name = ? LIMIT 1),\n" + // airport found
                "(SELECT id FROM colors WHERE name = ? LIMIT 1),\n" + // primary color
                "(SELECT id FROM colors WHERE name = ? LIMIT 1),\n" + // secondary color
                "?,\n" + // width
                "?,\n" + // length
                "?,\n" + // height
                "?,\n" + // weight
                "(SELECT id FROM passengers WHERE last_name = @last_name AND city = @city LIMIT 1),\n" +
                "?);"; // notes

        List<Object[]> params = new ArrayList<>();


        // reading sheets
        for (Sheet sheet : workbook) {
            // reading rows and retrieving params
            Iterator<Row> rowIterator = sheet.rowIterator();

            int i = 1;
            String airport = null;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (i == 2) {
                    airport = row.getCell(2).getStringCellValue();
                } else if (i >= 5) {
                    Object[] param = paramsFromRow(row, airport);
                    params.add(param);
                }
                i++;
            }
        }

        // TODO fix batch query
        for (Object[] param : params) {
            if (param == null)
                break;

            JDBC.getInstance().executeUpdateQuery(query, param);
        }

        return true;
//        // converting to 2d array
//        Object[][] paramsArray = new Object[params.size()][];
//        for (int i = 0; i < params.size(); i++) {
//            paramsArray[i] = params.get(i);
//        }
//
//        return JDBC.getInstance().executeBatchQuery(query, paramsArray);
    }

    /**
     * Converts a list of luggges to a Excel sheet.
     *
     * @param luggages The luggages to be converted.
     * @param file The file to write to.
     * @throws IOException
     */
    public static void toExcel(List<Luggage> luggages, File file) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row columnRow = sheet.createRow(0);
        columnRow.createCell(COLUMN_REGISTRATION_NUMBER).setCellValue("Registration Nr.");
        columnRow.createCell(COLUMN_DATE_FOUND).setCellValue("Date found");
        columnRow.createCell(COLUMN_TIME_FOUND).setCellValue("Time found");
        columnRow.createCell(COLUMN_LUGGAGE_TYPE).setCellValue("Luggage Type");
        columnRow.createCell(COLUMN_BRAND).setCellValue("Brand");
        columnRow.createCell(COLUMN_FLIGHT_NUMBER).setCellValue("Arrived with flight");
        columnRow.createCell(COLUMN_LUGGAGE_TAG).setCellValue("Luggage tag");
        columnRow.createCell(COLUMN_LOCATION_FOUND).setCellValue("Location Found");
        columnRow.createCell(COLUMN_MAIN_COLOR).setCellValue("Main color");
        columnRow.createCell(COLUMN_SECOND_COLOR).setCellValue("Second color");
        columnRow.createCell(COLUMN_SIZE).setCellValue("Size");
        columnRow.createCell(COLUMN_WEIGHT).setCellValue("Weight");
        columnRow.createCell(COLUMN_PASSENGER_AND_CITY).setCellValue("Passenger name, city");
        columnRow.createCell(COLUMN_OTHER_CHARACTERISTICS).setCellValue("Other characteristics");

        for (int i = 0; i < luggages.size(); i++) {
            // starting a row 1 because of the column row
            Row row = sheet.createRow(i + 1);
            Luggage luggage = luggages.get(i);

            fillRow(row, luggage);
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
    }

    /**
     * Converts a {@link Row} to a {@link Luggage}.
     *
     * @param row The row to be converted.
     * @param airport The airport.
     * @return The retrieved {@link Luggage}.
     */
    private static Object[] paramsFromRow(Row row, String airport) throws ParseException {
        Object[] params = new Object[16];

        // registration number
        params[0] = getCellValue(row, COLUMN_FLIGHT_NUMBER);

        // luggage tag
        params[1] = getCellValue(row, COLUMN_LUGGAGE_TAG);

        // passenger
        Cell passengerNameAndCityCell = row.getCell(COLUMN_PASSENGER_AND_CITY);
        if (passengerNameAndCityCell != null) {
            String passengerNameAndCity = passengerNameAndCityCell.getStringCellValue();
            String[] split = passengerNameAndCity.split(",", 2);
            if (split.length == 2) {
                String lastName = split[0].split("^[^\\s]*\\s", 1)[0];
                String city = split[1];

                params[2] = lastName;
                params[3] = city;
            }
        }

        // registration number
        params[4] = getCellValue(row, COLUMN_REGISTRATION_NUMBER);
        if (params[4] == null)
            return null;

        // date
        Object rawDateFound = getCellValue(row, COLUMN_DATE_FOUND);
        Object rawTimeFound = getCellValue(row, COLUMN_TIME_FOUND);

        Date dateFound;
        Date timeFound;

        if (rawDateFound instanceof Date && rawTimeFound instanceof Date) {
            dateFound = (Date) rawDateFound;
            timeFound = (Date) rawTimeFound;

        } else if (rawDateFound instanceof Double && rawTimeFound instanceof Double) {
            Double rawDateFoundDouble = (Double) rawDateFound;
            Double rawTimeFoundDouble = (Double) rawTimeFound;

            dateFound = DateUtil.getJavaDate(rawDateFoundDouble);
            timeFound = DateUtil.getJavaDate(rawTimeFoundDouble);

        } else {
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

            String rawDateFoundString = (String) rawDateFound;
            String rawTimeFoundString = (String) rawTimeFound;

            dateFound = dateFormatter.parse(rawDateFoundString);
            timeFound = timeFormatter.parse(rawTimeFoundString);
        }

        params[5] = Utils.changeTime(dateFound, timeFound);

        // luggage type
        params[6] = getCellValue(row, COLUMN_LUGGAGE_TYPE);

        // brand
        params[7] = getCellValue(row, COLUMN_BRAND);

        // location found
        params[8] = airport;

        // colors
        params[9] = getCellValue(row, COLUMN_MAIN_COLOR);
        params[10] = getCellValue(row, COLUMN_SECOND_COLOR);

        String sizes = row.getCell(COLUMN_SIZE).getStringCellValue();
        String[] parsedSizes = sizes.split("x", 3);

        // width, length, height
        if (sizes.length() == 3){
            params[11] = Integer.valueOf(parsedSizes[1]); // width
            params[12] = Integer.valueOf(parsedSizes[0]); //length
            params[13] = Integer.valueOf(parsedSizes[2]); // height
        }

        // weight
        String rawWeight = row.getCell(COLUMN_WEIGHT).getStringCellValue();
        String parsedWeight = rawWeight.replaceAll("kg", "");
        params[14] = Utils.parseInt(parsedWeight);

        params[15] = getCellValue(row, COLUMN_OTHER_CHARACTERISTICS);

        return params;
    }

    private static void fillRow(Row row, Luggage luggage) {
        row.createCell(COLUMN_REGISTRATION_NUMBER).setCellValue(luggage.getId());

        String dateFound = Utils.dateToString(luggage.getDateFound());
        row.createCell(COLUMN_DATE_FOUND).setCellValue(dateFound);

        String timeFound = Utils.dateToTimeString(luggage.getDateFound());
        row.createCell(COLUMN_TIME_FOUND).setCellValue(timeFound);

        String luggageType = luggage.getLuggagetType().getName();
        row.createCell(COLUMN_LUGGAGE_TYPE).setCellValue(luggageType);

        String brand = luggage.getBrand().getName();
        row.createCell(COLUMN_BRAND).setCellValue(brand);

        String flightNumber = luggage.getLabel().getFlight().getFlightNumber();
        row.createCell(COLUMN_FLIGHT_NUMBER).setCellValue(flightNumber);

        String luggageTag = luggage.getLabel().getTag();
        row.createCell(COLUMN_LUGGAGE_TAG).setCellValue(luggageTag);

        String locationFound = luggage.getLocationFound().getName();
        row.createCell(COLUMN_LOCATION_FOUND).setCellValue(locationFound);

        String mainColor = luggage.getPrimaryColor().getName();
        row.createCell(COLUMN_MAIN_COLOR).setCellValue(mainColor);

        String secondColor = luggage.getSecondaryColor().getName();
        row.createCell(COLUMN_SECOND_COLOR).setCellValue(secondColor);

        Integer length = luggage.getSizeLength();
        Integer width = luggage.getSizeWidth();
        Integer height = luggage.getSizeHeight();
        String size = String.format("%dx%dx%d", length, width, height);
        row.createCell(COLUMN_SIZE).setCellValue(size);

        Integer weight = luggage.getWeight();
        String weightWithNotation = String.format("%dkg", weight);
        row.createCell(COLUMN_WEIGHT).setCellValue(weightWithNotation);

        String passengerFirstName = luggage.getPassenger().getFirstName();
        String passengerMiddleName = luggage.getPassenger().getFirstName();
        String passengerLastName = luggage.getPassenger().getFirstName();
        String passengerCity = luggage.getPassenger().getCity();
        String passengerAndCity = String.format("%s %s %s, %s",
                passengerFirstName,
                passengerMiddleName,
                passengerLastName,
                passengerCity);

        row.createCell(COLUMN_PASSENGER_AND_CITY).setCellValue(passengerAndCity);

        String otherCharacteristics = luggage.getCharacteristics();
        row.createCell(COLUMN_OTHER_CHARACTERISTICS).setCellValue(otherCharacteristics);
    }

    private static Object getCellValue(Row row, int i) {
        Cell cell = row.getCell(i);

        if (cell == null)
            return null;

        switch (cell.getCellTypeEnum()) {
            case _NONE:
                return null;
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }
}
