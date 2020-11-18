/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'statuses' table inside the database and contains
 * all associated queries.
 *
 * @author Dylan Tweebeeke
 * @author Tom J. Wassing
 */
public class StatusesTable extends DatabaseTable<Status> {

    /**
     * Returns a list of all statuses in the table with a maximum.
     *
     * @param size the maximum of items to return
     * @return the statuses
     */
    @Override
    public List<Status> getAll(int size) {
        String query = "SELECT statuses.id, statuses.name FROM statuses LIMIT ?;";

        List<Status> statuses = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Status status = new Status(name, id);
                statuses.add(status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return statuses;
    }

    /**
     * Inserts a new entry into the 'statuses' table and returns true if the
     * query succeeded.
     *
     * @param status the new entry
     * @return if the query succeeded
     */
    @Override
    public boolean insert(Status status) {
        String query = "INSERT INTO statuses(name) VALUES (?);";

        return getJdbc().executeUpdateQuery(query, status.getName());
    }

    /**
     * Updates a entry in the 'statuses' table and returns true if the query
     * succeeded.
     *
     * @param status the entry to update
     * @return if the query succeeded
     */
    @Override
    public boolean update(Status status) {
        String query = "UPDATE statuses SET name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, status.getName(), status.getId());
    }

    /**
     * Deletes a entry from the 'statuses' table and returns true if the query
     * succeeded.
     *
     * @param status the entry to delete
     * @return if the query succeeded
     */
    @Override
    public boolean delete(Status status) {
        String query = "DELETE FROM statuses WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, status.getId());
    }

}
