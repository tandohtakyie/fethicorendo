/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'colors' table inside the database and contains all
 * associated queries.
 *
 * @author Tom J. Wassing
 */
public class ColorsTable extends DatabaseTable<Color> {

    /**
     * This methods retrieves all colors from the table colors and parses it to
     * {@link Color} objects.
     *
     * @param size the list size
     * @return a list of all colors
     */
    @Override
    public List<Color> getAll(int size) {
        String query = "SELECT id, ral_code, name FROM colors LIMIT ?;";

        List<Color> colors = new ArrayList<>();

        // executing query
        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            // reading results and parsing to color
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                int ralCode = rs.getInt("ral_code");
                String name = rs.getString("name");

                Color color = new Color(name, ralCode, id);
                colors.add(color);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return colors;
    }

    /**
     * This method inserts a new color entry into the colors table in the
     * database.
     *
     * @param color the new color
     * @return if the insert operation succeeded
     */
    @Override
    public boolean insert(Color color) {
        String query = "INSERT INTO colors (name) VALUES (?);";

        return getJdbc().executeUpdateQuery(query, color.getName());
    }

    /**
     * This method updates a color entry from the colors table in the database.
     *
     * @param color the updated color
     * @return if the update operation succeeded
     */
    @Override
    public boolean update(Color color) {
        String query = "UPDATE colors SET name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, color.getName(), color.getId());
    }

    /**
     * This method deletes a color entry from the colors table in the database.
     *
     * @param color the color
     * @return if the delete operation succeeded
     */
    @Override
    public boolean delete(Color color) {
        String query = "DELETE FROM colors WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, color.getId());
    }

}
