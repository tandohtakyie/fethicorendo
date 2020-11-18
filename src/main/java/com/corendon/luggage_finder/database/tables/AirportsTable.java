/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Airport;
import com.corendon.luggage_finder.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'airports' table inside the database and contains
 * all associated queries.
 *
 * @author Tom J. Wassing
 */
public class AirportsTable extends DatabaseTable<Airport> {

    /**
     * This methods retrieves all airports from the table airports and parses it
     * to {@link Airport} objects.
     *
     * @param size the list size
     * @return a list of all colors
     */
    @Override
    public List<Airport> getAll(int size) {
        String query = "SELECT airports.id, airports.name, timezone, daylight_saving, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name "
                + "FROM airports "
                + "INNER JOIN countries ON airports.country_id = countries.id "
                + "LIMIT ?;";

        List<Airport> airports = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryName, countryCode, countryId);

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int timezone = rs.getInt("timezone");
                boolean daylightSaving = rs.getBoolean("daylight_saving");

                Airport airport = new Airport(name, country, timezone, daylightSaving, id);

                airports.add(airport);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return airports;
    }

    /**
     * Retrieves a airport by its name.
     *
     * @param airportName The airport name.
     * @return The retrieved airport.
     */
    public Airport getByName(String airportName) {
        String query = "SELECT id, name, timezone, daylight_saving, "
                + "c.id AS country_id, c.code AS country_code, c.name AS country_name "
                + "FROM airports "
                + "INNER JOIN countries c ON airports.country_id = c.id "
                + "WHERE name = ? "
                + "LIMIT 1;";

        Airport airport = null;

        try (ResultSet rs = getJdbc().executeSelectQuery(query, airportName)) {
            if (rs != null && rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryName, countryCode, countryId);

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int timezone = rs.getInt("timezone");
                boolean daylightSaving = rs.getBoolean("daylight_saving");

                airport = new Airport(name, country, timezone, daylightSaving, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airport;
    }

    /**
     * This method inserts a new airport entry into the airports table.
     *
     * @param airport the new airport
     * @return if the insert operation succeeded
     */
    @Override
    public boolean insert(Airport airport) {
        String query = "INSERT INTO airports (name, country_id, timezone, daylight_saving) VALUES (?, ?, ?, ?);";

        return getJdbc().executeUpdateQuery(query,
                airport.getName(),
                airport.getCountry().getId(),
                airport.getTimezone(),
                airport.isDaylightSaving());
    }

    /**
     * This method updates a airport entry in the airports table.
     *
     * @param airport the updated airport
     * @return if the update operation succeeded
     */
    @Override
    public boolean update(Airport airport) {
        String query = "UPDATE airports SET name = ?, country_id = ?, timezone = ?, daylight_saving = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query,
                airport.getName(),
                airport.getCountry().getId(),
                airport.getTimezone(),
                airport.isDaylightSaving(),
                airport.getId());
    }

    /**
     * This method deletes a airport entry from the airports table.
     *
     * @param airport the airport
     * @return if the delete operation succeeded
     */
    @Override
    public boolean delete(Airport airport) {
        String query = "DELETE FROM airports WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, airport.getId());
    }

}
