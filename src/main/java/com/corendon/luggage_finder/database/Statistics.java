/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to query database for statistic related queries.
 *
 * @author pepi
 */
public class Statistics {

    private JDBC jdbc;

    /**
     * {@inheritDoc}
     *
     * @see Object#Statistics()
     */
    public Statistics() {
        this.jdbc = JDBC.getInstance();
    }

    /**
     * Generate hashmap containing the amount of luggages having status.
     *
     * @param fromDate Start date of rapport.
     * @param toDate End date of rapport.
     * @return {@link HashMap} with the status as key and amount of luggages as
     * value.
     */
    public Map<String, Number> generateLuggageStatusRapport(Date fromDate, Date toDate) {
        String query = "SELECT result_table.name AS name, COUNT(result_table.name) AS count "
                + "FROM ( "
                + "SELECT a.luggage_id, statuses.name, a.created_timestamp "
                + "FROM statuses_history AS a "
                + "INNER JOIN statuses ON a.status_id = statuses.id "
                + "INNER JOIN ( "
                + "SELECT luggage_id, MAX(statuses_history.created_timestamp) AS max_timestamp "
                + "FROM statuses_history "
                + "INNER JOIN luggages ON statuses_history.luggage_id = luggages.id "
                + "WHERE luggages.created_timestamp >= ? AND luggages.created_timestamp < ? "
                + "GROUP BY luggage_id "
                + ") AS b ON a.luggage_id = b.luggage_id AND a.created_timestamp = b.max_timestamp "
                + ") AS result_table "
                + "GROUP BY result_table.name;";

        Map<String, Number> resultMap = new HashMap<>();

        try (ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)) {
            while (rs != null && rs.next()) {
                String name = rs.getString("name").toString();
                int amount = rs.getInt("count");

                resultMap.put(name, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }

    /**
     * Generate an hashmap containing the total luggages found on airports.
     *
     * @param fromDate The starting date of the rapport.
     * @param toDate The end date of the rapport.
     * @return {@link HashMap} containing the airports as key and luggages found
     * as value.
     */
    public Map<String, Number> generateRapportFoundLocations(Date fromDate, Date toDate) {
        String query = "SELECT COUNT(luggages.id) AS count, airports.name AS name "
                + "FROM luggages "
                + "INNER JOIN airports ON luggages.location_found_id = airports.id "
                + "WHERE luggages.created_timestamp >= ? AND luggages.created_timestamp <= ? "
                + "GROUP BY name;";

        Map<String, Number> resultMap = new HashMap<>();

        try (ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)) {
            while (rs != null && rs.next()) {
                String name = rs.getString("name").toString();
                int amount = rs.getInt("count");

                resultMap.put(name, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }

//    public Map<String, Number> generateLuggagesInsuranceRapport(Date fromDate, Date toDate) {
//        String query = "SELECT COUNT(luggages.id) AS count, insurance_companies.name AS name "
//                + "FROM luggages "
//                + "INNER JOIN insurance_companies ON luggages.insurance_company_id = insurance_companies.id "
//                + "WHERE luggages.created_timestamp >= ? AND luggages.created_timestamp <= ? "
//                + "GROUP BY name;";
//        
//        Map<String, Number> resultMap = new HashMap<>();
//        
//        try(ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)){
//            while ()
//        }
//    }

    /**
     * Generates an hashmap containing the amount of luggages added per day.
     *
     * @param fromDate The date to get the results from.
     * @param toDate The date to get the results to.
     * @return {@link HashMap} with the results. Each key is a day.
     */
    public Map<String, Number> generateRapportByDay(Date fromDate, Date toDate) {
        String query = "SELECT COUNT(id) AS count, DAY(created_timestamp) AS day "
                + "FROM luggages "
                + "WHERE created_timestamp >= ? AND created_timestamp <= ? "
                + "GROUP BY DAY(created_timestamp);";

        Map<String, Number> resultMap = new HashMap<>();

        try (ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)) {
            while (rs != null && rs.next()) {
                String date = Integer.toString(rs.getInt("day"));
                int amount = rs.getInt("count");

                resultMap.put(date, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }

    /**
     * Generates an hashmap containing the amount of luggages added per month.
     *
     * @param fromDate The date to get the results from.
     * @param toDate The date to get the results to.
     * @return {@link HashMap} with the results. Each key is a month.
     */
    public Map<String, Number> generateRapportByMonth(Date fromDate, Date toDate) {
        String query = "SELECT COUNT(id) AS count, MONTH(created_timestamp) AS month "
                + "FROM luggages "
                + "WHERE created_timestamp >= ? AND created_timestamp <= ? "
                + "GROUP BY MONTH(created_timestamp);";

        Map<String, Number> resultMap = new HashMap<>();

        try (ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)) {
            while (rs != null && rs.next()) {
                String date = Integer.toString(rs.getInt("month"));
                int amount = rs.getInt("count");

                resultMap.put(date, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }

    /**
     * Generates an hashmap containing the amount of luggages added per year.
     *
     * @param fromDate The date to get the results from.
     * @param toDate The date to get the results to.
     * @return {@link HashMap} with the results. Each key is a year.
     */
    public Map<String, Number> generateRapportByYear(Date fromDate, Date toDate) {
        String query = "SELECT COUNT(id) AS count, YEAR(created_timestamp) AS year "
                + "FROM luggages "
                + "WHERE created_timestamp >= ? AND created_timestamp <= ? "
                + "GROUP BY YEAR(created_timestamp);";

        Map<String, Number> resultMap = new HashMap<>();

        try (ResultSet rs = jdbc.executeSelectQuery(query, fromDate, toDate)) {
            while (rs != null && rs.next()) {
                String date = Integer.toString(rs.getInt("year"));
                int amount = rs.getInt("count");

                resultMap.put(date, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }
}
