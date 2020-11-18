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
import com.corendon.luggage_finder.model.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vince de Leeuw
 */
public class LabelsTable extends DatabaseTable<Label> {

    /**
     * This class contains the "labels" table inside of the database, with all
     * the the necessary data and queries
     *
     * @param size the list size
     * @return returns all in the database
     */
    @Override
    public List<Label> getAll(int size) {
        String query = "SELECT id, tag, "
                + "flights.id AS flights_id, flights.flightnumber AS flights_flightnumber, "
                + "airports.name "
                + "FROM labels "
                + "INNER JOIN flights ON labels.flight_id = flights.id "
                + "LIMIT ?;";

        List<Label> labels = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int flightId = rs.getInt("flights_id");
                String flightNumber = rs.getString("flights_flightnumber");

                Flight flight = new Flight(flightNumber, flightId);

                int id = rs.getInt("id");
                String tag = rs.getString("tag");

                Label label = new Label(tag, flight, id);
                labels.add(label);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return labels;
    }

    /**
     * The insert method which inserts the information into the labels table
     *
     * @param label the new label
     * @return returns if the insert was succesful
     */
    @Override
    public boolean insert(Label label) {
        String query = "INSTER INTO labels (id, tag, flights_id) VALUES (?, ?, ?)";
        return getJdbc().executeUpdateQuery(query,
                label.getId(),
                label.getTag(),
                label.getFlight().getId());
    }

    /**
     * The update method which updates a part of the labels table
     *
     * @param label the updated label
     * @return returns the updated query if it succeeded
     */
    @Override
    public boolean update(Label label) {
        String query = "UPDATE labels SET id = ?, tag = ?, flights_id = ? WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query,
                label.getId(),
                label.getTag(),
                label.getFlight().getId(),
                label.getId());
    }

    /**
     * this method will delete the an entry in the labels table
     *
     * @param label the deleted label
     * @return returns the updated query if it succeeded
     */
    @Override
    public boolean delete(Label label) {
        String query = "DELETE FROM labels WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query, label.getId());
    }

}
