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
import com.corendon.luggage_finder.model.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the "flights" table inside of the database, with all the
 * the necessary data and queries
 *
 * @author Vince de Leeuw
 */
public class FlightsTable extends DatabaseTable<Flight> {

    /**
     * Makes a query to be able to retrieve data for the flight class with a
     * join between the airport and country tables
     *
     * @param size the size of the list
     * @return returns all in the database
     */
    @Override
    public List<Flight> getAll(int size) {
        String query = "SELECT id, flightnumber, "
                + "FROM flights "
                + "LIMIT ?;";

        List<Flight> flights = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String flightNumber = rs.getString("flightnumber");

                Flight flight = new Flight(flightNumber, id);
                flights.add(flight);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return flights;
    }

    /**
     * The insert method which inserts the information into the flights table
     *
     * @param flight the new flight
     * @return returns if the insert was succesful
     */
    @Override
    public boolean insert(Flight flight) {
        String query = "INSERT INTO flights (id, flightnumber) VALUE (?, ?)";
        return getJdbc().executeUpdateQuery(query,
                flight.getId(),
                flight.getFlightNumber());
    }

    /**
     * this method updates the information in the flight table
     *
     * @param flight the updated flight
     * @return returns the updated information if it succeded
     */
    @Override
    public boolean update(Flight flight) {
        String query = "UPDATE flights SET id = ?, flightnumber = ?) WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query,
                flight.getId(),
                flight.getFlightNumber(),
                flight.getId());

    }

    /**
     * this method will delete the an entry in the flight table
     *
     * @param flight the deleted flight object
     * @return returns the updated query if it succeeded
     */
    @Override
    public boolean delete(Flight flight) {
        String query = "DELETE FROM flights WHERE id =?;";
        return getJdbc().executeUpdateQuery(query, flight.getId());
    }

}
