/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the "functions" table.
 *
 * @author Peter Dimitrov
 */
public class FunctionsTable extends DatabaseTable<Function> {

    /**
     * Get all entries from functions table.
     *
     * @param size Amount of entries to return.
     * @return List of {@link Function} entries.
     */
    @Override
    public List<Function> getAll(int size) {
        String query = "SELECT id, name FROM functions LIMIT ?;";
        List<Function> functions = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Function function = new Function(name, id);

                functions.add(function);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return functions;
    }

    /**
     * Insert entry into functions table.
     *
     * @param entry {@link Function} entry.
     * @return Success.
     */
    @Override
    public boolean insert(Function entry) {
        String query = "INSERT INTO functions (id, name) VALUES (?, ?);";

        return getJdbc().executeUpdateQuery(query, entry.getId(), entry.getName());
    }

    /**
     * Update entry in functions table.
     *
     * @param entry {@link Function} entry to update.
     * @return Success.
     */
    @Override
    public boolean update(Function entry) {
        String query = "UPDATE functions SET name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, entry.getName(), entry.getId());
    }

    /**
     * Delete entry from functions table.
     *
     * @param entry {@link Function} entry to delete.
     * @return Success.
     */
    @Override
    public boolean delete(Function entry) {
        String query = "DELETE FROM functions WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, entry.getId());
    }
}
