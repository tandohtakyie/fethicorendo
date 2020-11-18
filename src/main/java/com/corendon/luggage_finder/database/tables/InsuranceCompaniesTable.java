/* 
 *Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Insurance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yessin el Khaldi
 */
public class InsuranceCompaniesTable extends DatabaseTable<Insurance> {

    @Override
    public List<Insurance> getAll(int size) {
        String query = "SELECT id, name "
                + "FROM insurance_companies "
                + "LIMIT ?;";

        List<Insurance> insurances = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Insurance insurance = new Insurance(name, id);

                insurances.add(insurance);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return insurances;
    }

    @Override
    public boolean insert(Insurance entry) {
        String query = "INSERT INTO insurance_companies (name) VALUES (?);";

        return getJdbc().executeUpdateQuery(query,
                entry.getName());
    }

    @Override
    public boolean update(Insurance entry) {
        String query = "UPDATE inurance_companies SET name = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query,
                entry.getName(),
                entry.getId());
    }

    @Override
    public boolean delete(Insurance entry) {
        String query = "DELETE FROM insurance_companies WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query,
                entry.getId());

    }

}
