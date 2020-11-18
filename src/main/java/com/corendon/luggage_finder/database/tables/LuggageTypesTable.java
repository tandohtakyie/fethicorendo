/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.LuggageType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fethi K. Tewelde
 */
public class LuggageTypesTable extends DatabaseTable<LuggageType> {

    /**
     * This methods retrieves all luggage types from the table luggage_types
     * pass and parses it to {@link LuggageType} objects.
     *
     * @param size the list size
     * @return a list of all luggage_types
     */
    @Override
    public List<LuggageType> getAll(int size) {
        String query = "SELECT id, name FROM luggage_types LIMIT ?;";

        List<LuggageType> luggage_types = new ArrayList<>();

        // executing query
        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            // reading results and parsing to luggageType
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                LuggageType luggageType = new LuggageType(name, id);
                luggage_types.add(luggageType);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return luggage_types;
    }

    /**
     * This method inserts a new luggageType entry into the luggage_types table
     * in the database.
     *
     * @param luggageType the new luggage_types
     * @return if the insert operation succeeded
     */
    @Override
    public boolean insert(LuggageType luggageType) {

        String query = "INSERT INTO luggage_types (name) VALUES (?);";
        return getJdbc().executeUpdateQuery(query, luggageType.getName());
    }

    /**
     * This method updates a LuggageType entry from the luggage_types table in
     * the database.
     *
     * @param luggageType the updated luggage_types
     * @return if the update operation succeeded
     */
    @Override
    public boolean update(LuggageType luggageType) {
        String query = "UPDATE luggage_types SET name = ? WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query, luggageType.getName(), luggageType.getId());
    }

    /**
     * This method deletes a LuggageType entry from the luggage_types table in
     * the database.
     *
     * @param luggageType the luggage type
     * @return if the delete operation succeeded
     */
    @Override
    public boolean delete(LuggageType luggageType) {
        String query = "DELETE FROM luggage_types WHERE id = ?;";
        return getJdbc().executeUpdateQuery(query, luggageType.getId());
    }

}
