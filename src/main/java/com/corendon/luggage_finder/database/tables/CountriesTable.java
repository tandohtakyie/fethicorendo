/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the table "country" in the database.
 *
 * @author Peter Dimitrov
 */
public class CountriesTable extends DatabaseTable<Country> {

    /**
     * Get all data from country table.
     *
     * @param size Size limit of the returned list.
     * @return List of Country entries.
     */
    @Override
    public List<Country> getAll(int size) {
        String query = "SELECT id, code, name FROM countries LIMIT ?;";
        List<Country> countries = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");

                Country country = new Country(code, name, id);

                countries.add(country);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return countries;
    }

    /**
     * Insert entry into table.
     *
     * @param entry Country entry to insert.
     * @return Success.
     */
    @Override
    public boolean insert(Country entry) {
        String query = "INSERT INTO countries (code, name) VALUES (?, ?);";

        return getJdbc().executeUpdateQuery(query, entry.getCode(), entry.getName());
    }

    /**
     * Update an entry in the table.
     *
     * @param entry Entry to update with.
     * @return Success.
     */
    @Override
    public boolean update(Country entry) {
        String query = "UPDATE countries SET code = ?, name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, entry.getCode(), entry.getName(), entry.getId());
    }

    /**
     * Delete entry from table.
     *
     * @param entry Entry to delete.
     * @return Success.
     */
    @Override
    public boolean delete(Country entry) {
        String query = "DELETE FROM countries WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, entry.getId());
    }

}
