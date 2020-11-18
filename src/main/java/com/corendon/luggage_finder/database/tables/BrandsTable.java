/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'brands' table inside the database and contains all
 * associated queries.
 *
 * @author Dylan Tweebeeke
 */
public class BrandsTable extends DatabaseTable<Brand> {

    /**
     * gets all the items from the database
     *
     * @param size
     * @return
     */
    @Override
    public List<Brand> getAll(int size) {
        String query = "SELECT id, name FROM brands LIMIT ?;";

        List<Brand> brands = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {

            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Brand brand = new Brand(name, id);
                brands.add(brand);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return brands;
    }

    /**
     * inserts data into the database
     *
     * @param brand
     * @return
     */
    @Override
    public boolean insert(Brand brand) {
        String query = "INSERT INTO brands (name) VALUES (?);";

        return getJdbc().executeUpdateQuery(query, brand.getName());
    }

    /**
     * updates data into the database
     *
     * @param brand
     * @return
     */
    @Override
    public boolean update(Brand brand) {
        String query = "UPDATE brands SET name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, brand.getName(), brand.getId());
    }

    /**
     * deletes data from the database
     *
     * @param brand
     * @return
     */
    @Override
    public boolean delete(Brand brand) {
        String query = "DELETE FROM brands WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, brand.getId());
    }

}
