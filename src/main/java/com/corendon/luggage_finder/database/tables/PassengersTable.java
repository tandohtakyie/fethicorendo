/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Country;
import com.corendon.luggage_finder.model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the passengers table in the database.
 *
 * @author Fethi K. Tewelde
 * @author Petar Dimitrov
 */
public class PassengersTable extends DatabaseTable<Passenger> {

    /**
     * Gets the last passenger that is registered.
     *
     * @return {@link Passenger} which has been added to the table last.
     */
    public Passenger getLastRegisteredPassenger() {
        String query = "SELECT passengers.id AS passenger_id, first_name, IFNULL(middle_name, '') AS middle_name, last_name, address, postcode, city, email, phone, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name "
                + "FROM passengers "
                + "INNER JOIN countries ON passengers.country_id = countries.id "
                + "ORDER BY passengers.id DESC "
                + "LIMIT 1;";

        Passenger passenger = null;

        try (ResultSet rs = getJdbc().executeSelectQuery(query)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("passenger_id");

                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryCode, countryName, countryId);

                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("postcode");
                String city = rs.getString("city");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                passenger = new Passenger(first_name, middle_name, last_name, address, zipcode, city, country, email, phone, id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return passenger;
    }

    /**
     * search a passenger from passengers table.
     *
     * @param searchString Query to search for.
     * @return List of found passengers.
     */
    public List<Passenger> search(String searchString) {
        String query = "SELECT passengers.id AS passenger_id, first_name, IFNULL(middle_name, '') AS middle_name, last_name, address, postcode, city, email, phone, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name "
                + "FROM passengers "
                + "INNER JOIN countries ON passengers.country_id = countries.id "
                + "WHERE first_name LIKE ? OR "
                + "middle_name LIKE ? OR "
                + "last_name LIKE ? OR "
                + "address LIKE ? OR "
                + "postcode LIKE ? OR "
                + "city LIKE ? OR "
                + "email LIKE ? OR "
                + "phone LIKE ? OR "
                + "country_id LIKE ? OR "
                + "countries.code LIKE ? OR "
                + "countries.name LIKE ?;";

        List<Passenger> passengers = new ArrayList<>();

        searchString += "%";

        try (ResultSet rs = getJdbc().executeSelectQuery(query, searchString, searchString, searchString, searchString, searchString, searchString, searchString, searchString, searchString, searchString, searchString)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("passenger_id");

                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryCode, countryName, countryId);

                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("postcode");
                String city = rs.getString("city");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Passenger passenger = new Passenger(first_name, middle_name, last_name, address, zipcode, city, country, email, phone, id);

                passengers.add(passenger);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return passengers;
    }

    /**
     * This methods retrieves all passengers from the table passenger and parses
     * it to {@link Passenger} objects.
     *
     * @param size the list size
     * @return a list of all passengers
     */
    @Override
    public List<Passenger> getAll(int size) {
        String query = "SELECT passengers.id AS passenger_id, first_name, IFNULL(middle_name, '') AS middle_name, last_name, address, postcode, city, email, phone, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name "
                + "FROM passengers "
                + "INNER JOIN countries ON passengers.country_id = countries.id "
                + "LIMIT ?;";

        List<Passenger> passengers = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("passenger_id");

                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryCode, countryName, countryId);

                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("postcode");
                String city = rs.getString("city");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Passenger passenger = new Passenger(first_name, middle_name, last_name, address, zipcode, city, country, email, phone, id);

                passengers.add(passenger);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return passengers;
    }

    /**
     * This method inserts a new passenger entry into the passengers table.
     *
     * @param passenger the new passenger
     * @return if the insert operation succeeded
     */
    @Override
    public boolean insert(Passenger passenger) {
        String query = "INSERT INTO passengers (first_name, middle_name, last_name, address, postcode, city, country_id, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return getJdbc().executeUpdateQuery(query,
                passenger.getFirstName(),
                passenger.getMiddleName(),
                passenger.getLastName(),
                passenger.getAddress(),
                passenger.getZipcode(),
                passenger.getCity(),
                passenger.getCountry().getId(),
                passenger.getEmail(),
                passenger.getPhone());
    }

    /**
     * This method updates a passenger entry in the passengers table.
     *
     * @param passenger the updated passengers
     * @return if the update operation succeeded
     */
    @Override
    public boolean update(Passenger passenger) {
        String query = "UPDATE passengers SET first_name = ?, middle_name = ?, last_name = ?, address = ?, postcode = ?, city = ?, country_id = ?, email = ?, phone = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query,
                passenger.getFirstName(),
                passenger.getMiddleName(),
                passenger.getLastName(),
                passenger.getAddress(),
                passenger.getZipcode(),
                passenger.getCity(),
                passenger.getCountry().getId(),
                passenger.getEmail(),
                passenger.getPhone(),
                passenger.getId());
    }

    /**
     * This method deletes a passenger entry from the passengers table.
     *
     * @param passenger the passenger
     * @return if the delete operation succeeded
     */
    @Override
    public boolean delete(Passenger passenger) {
        String query = "DELETE FROM passengers WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query, passenger.getId());
    }
}
