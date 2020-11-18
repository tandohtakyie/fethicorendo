/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.*;
import com.sun.istack.internal.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents the 'luggages' table inside the database and contains
 * all associated queries.
 *
 * @author Tom J. Wassing
 * @author Yessin el Khaldi
 */
public class LuggagesTable extends DatabaseTable<Luggage> {

    public LuggagesTable() {
        System.out.println(getSelectQuery());
    }


    /**
     * Returns all {@link Luggage} that match the pattern of the search term and
     * match the filters.
     *
     * @param searchQuery the search term
     * @param from starting date
     * @param until ending date
     * @param type luggage type
     * @param primaryColor primary color
     * @param secondaryColor secondary color
     * @param brand brand
     * @param fromWeight starting weight
     * @param untilWeight ending weight
     * @param insurance
     * @param size limit of items to return
     * @return list of luggages that match the search term and filters
     */
    public List<Luggage> search(String searchQuery, @Nullable Date from,
            @Nullable Date until, @Nullable LuggageType type,
            @Nullable Color primaryColor, @Nullable Color secondaryColor,
            @Nullable Brand brand, @Nullable Integer fromWeight,
            @Nullable Integer untilWeight, @Nullable Insurance insurance, int size) {

        StringBuilder builder = new StringBuilder();
        builder.append(getSelectQuery());
        builder.append("WHERE luggages.registration_id LIKE ?");

        List<Object> params = new ArrayList<>();

        searchQuery += "%";
        params.add(searchQuery);

        if (from != null) {
            builder.append(" AND luggages.created_timestamp >= ?");
            Timestamp stamp = new Timestamp(from.getTime());
            params.add(stamp);
        }

        if (until != null) {
            builder.append(" AND luggages.created_timestamp <= ?");
            Timestamp stamp = new Timestamp(until.getTime());
            params.add(stamp);
        }

        if (type != null) {
            builder.append(" AND luggages.luggage_type_id = ?");
            params.add(type.getId());
        }

        if (primaryColor != null) {
            builder.append(" AND luggages.primary_color_id = ?");
            params.add(primaryColor.getId());
        }

        if (secondaryColor != null) {
            builder.append(" AND luggages.secondary_color_id = ?");
            params.add(secondaryColor.getId());
        }

        if (brand != null) {
            builder.append(" AND luggages.brand_id = ?");
            params.add(brand.getId());
        }

        if (fromWeight != null) {
            builder.append(" AND luggages.weight >= ?");
            params.add(fromWeight);
        }

        if (untilWeight != null) {
            builder.append(" AND luggages.weight <= ?");
            params.add(untilWeight);
        }
        
        if (insurance != null){
            builder.append(" AND luggages.insurance_company_id = ?");
            params.add(insurance.getId());
            
        }
        


        builder.append(" GROUP BY luggages.id");
        builder.append(" LIMIT ?;");
        params.add(size);

        List<Luggage> luggages = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(builder.toString(), params.toArray())) {
            while (rs != null && rs.next()) {
                Luggage luggage = parseResultSet(rs);
                luggages.add(luggage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return luggages;
    }

    public List<Luggage> getBetweenDates(Date startDate, Date endDate) {
        String query = getSelectQuery() + "WHERE luggages.created_timestamp BETWEEN ? AND ? GROUP BY luggages.id ;";

        Timestamp startStamp = new Timestamp(startDate.getTime());
        Timestamp endStamp = new Timestamp(endDate.getTime());

        List<Luggage> luggages = new ArrayList<>();
        try (ResultSet rs = getJdbc().executeSelectQuery(query, startStamp, endStamp)) {
            while (rs != null && rs.next()) {
                Luggage luggage = parseResultSet(rs);
                luggages.add(luggage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return luggages;
    }

    public Luggage getById(int id) {
        String query = getSelectQuery()
                + "WHERE luggages.id = ?\n"
                + "GROUP BY luggages.id "
                + "LIMIT 1;";

        Luggage luggage = null;
        try (ResultSet rs = getJdbc().executeSelectQuery(query, id)) {
            if (rs != null && rs.next()) {
                luggage = parseResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return luggage;
    }

    /**
     * Returns a list of all {@link Luggage} in the table with a maximum.
     *
     * @param size the maximum of items to return
     * @return list of the luggages
     */
    @Override
    public List<Luggage> getAll(int size) {
        String query = getSelectQuery()
                + "GROUP BY luggages.id "
                + "LIMIT ?;";

        List<Luggage> luggages = new ArrayList<>();
        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                Luggage luggage = parseResultSet(rs);
                luggages.add(luggage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return luggages;
    }

    /**
     * Inserts a new entry into the 'luggages' table and returns true if the
     * query succeeded.
     *
     * @param luggage the new entry
     * @return if the query succeeded
     */
    @Override
    public boolean insert(Luggage luggage) {
        String query = "INSERT INTO luggages (registration_id, date_found, luggage_type_id, "
                + "brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, "
                + "size_width, size_length, size_height, weight, passenger_id, "
                + "other_characteristics, insurance_company_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        Timestamp stamp = new Timestamp(luggage.getDateFound().getTime());

        Integer luggageTypeId = Utils.getIdOrNull(luggage.getLuggagetType());
        Integer brandId = Utils.getIdOrNull(luggage.getBrand());
        Integer labelId = Utils.getIdOrNull(luggage.getLabel());
        Integer foundAirportId = Utils.getIdOrNull(luggage.getLocationFound());
        Integer primaryColorId = Utils.getIdOrNull(luggage.getPrimaryColor());
        Integer secondaryColorId = Utils.getIdOrNull(luggage.getSecondaryColor());
        Integer passengerId = Utils.getIdOrNull(luggage.getPassenger());
        Integer insuranceId = Utils.getIdOrNull(luggage.getInsurance());

        return getJdbc().executeUpdateQuery(query,
                luggage.getRegistrationId(),
                stamp,
                luggageTypeId,
                brandId,
                labelId,
                foundAirportId,
                primaryColorId,
                secondaryColorId,
                luggage.getSizeWidth(),
                luggage.getSizeLength(),
                luggage.getSizeHeight(),
                luggage.getWeight(),
                passengerId,
                luggage.getCharacteristics(),
                insuranceId);

    }

    /**
     * Updates a entry in the 'luggages' table and returns true if the query
     * succeeded.
     *
     * @param luggage the entry to update
     * @return if the query succeeded
     */
    @Override
    public boolean update(Luggage luggage) {
        String query = "UPDATE luggages SET registration_id = ?, date_found = ?, luggage_type_id = ?, "
                + "brand_id= ?, label_id = ?, location_found_id = ?, primary_color = ?, secondary_color = ?, "
                + "size_width = ?, size_length = ?, size_height = ?, weight = ?, passenger_id = ?, "
                + "other_characteristics = ?, insurance_company_id = ?, WHERE id = ?;";

        Timestamp stamp = new Timestamp(luggage.getDateFound().getTime());

        return getJdbc().executeUpdateQuery(query,
                luggage.getRegistrationId(),
                stamp,
                luggage.getLuggagetType(),
                luggage.getBrand().getId(),
                luggage.getLabel().getId(),
                luggage.getLocationFound().getId(),
                luggage.getPrimaryColor().getId(),
                luggage.getSecondaryColor().getId(),
                luggage.getSizeWidth(),
                luggage.getSizeLength(),
                luggage.getSizeHeight(),
                luggage.getWeight(),
                luggage.getPassenger().getId(),
                luggage.getCharacteristics(),
                luggage.getInsurance());
    }

    /**
     * Deletes a entry from the 'luggages' table and returns true if the query
     * succeeded.
     *
     * @param luggage the entry to delete
     * @return if the query succeeded
     */
    @Override
    public boolean delete(Luggage luggage) {
        String query = "DELETE FROM luggages WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, luggage.getId());
    }

    /**
     * Returns the select query.
     *
     * @return the query
     */
    private String getSelectQuery() {
        return "SELECT luggages.id AS luggage_id, "
                + "luggages.registration_id AS luggage_registration_id, "
                + "statuses.id AS status_id, "
                + "statuses.name AS status_name, "
                + "MAX(statuses_history.created_timestamp) AS status_timestamp, "
                + "date_found AS date_found, "
                + "luggage_types.id AS luggage_type_id, "
                + "luggage_types.name AS luggage_type_name, "
                + "brands.id AS brand_id, "
                + "brands.name AS brand_name, "
                + "labels.id AS label_id, "
                + "labels.tag AS label_tag, "
                + "label_flights.id AS label_flight_id, "
                + "label_flights.flightnumber AS label_flight_flightnumber, "
                + "found_on_airports.id AS airport_found_id, "
                + "found_on_airports.name AS airport_found_name, "
                + "found_on_airport_countries.id AS airport_found_country_id, "
                + "found_on_airport_countries.code AS airport_found_country_code, "
                + "found_on_airport_countries.name AS airport_found_country_name, "
                + "found_on_airports.timezone AS airport_found_timezone, "
                + "found_on_airports.daylight_saving AS airport_found_daylight_saving, "
                + "primary_colors.id AS primary_color_id, "
                + "primary_colors.ral_code AS primary_color_ral_code, "
                + "primary_colors.name AS primary_color_name, "
                + "secondary_colors.id AS secondary_color_id, "
                + "secondary_colors.ral_code AS secondary_color_ral_code, "
                + "secondary_colors.name AS secondary_color_name, "
                + "luggages.size_width AS size_width, "
                + "luggages.size_length AS size_length, "
                + "luggages.size_height AS size_height, "
                + "luggages.weight AS weight, "
                + "passengers.id AS passenger_id, "
                + "passengers.first_name AS passenger_first_name, "
                + "passengers.middle_name AS passenger_middle_name, "
                + "passengers.last_name AS passenger_last_name, "
                + "passengers.address AS passenger_address, "
                + "passengers.postcode AS passenger_postcode, "
                + "passengers.city AS passenger_city, "
                + "passenger_countries.id AS passenger_country_id, "
                + "passenger_countries.code AS passenger_country_code, "
                + "passenger_countries.name AS passenger_country_name, "
                + "passengers.email AS passenger_email, "
                + "passengers.phone AS passenger_phone, "
                + "luggages.other_characteristics AS other_characteristics, "
                + "luggages.created_timestamp AS luggage_created_timestamp, "
                + "luggages.modified_timestamp AS luggage_modified_timestamp, "
                + "insurance_companies.id AS insurance_company_id, "
                + "insurance_companies.name AS insurance_company_name "
                + "FROM luggages "
                + "LEFT OUTER JOIN statuses_history ON luggages.id = statuses_history.luggage_id "
                + "LEFT OUTER JOIN statuses ON statuses.id = statuses_history.status_id "
                + "LEFT OUTER JOIN luggage_types ON luggages.luggage_type_id = luggage_types.id "
                + "LEFT OUTER JOIN brands ON luggages.brand_id = brands.id "
                + "LEFT OUTER JOIN labels ON luggages.label_id = labels.id "
                + "LEFT OUTER JOIN flights AS label_flights ON labels.flight_id = label_flights.id "
                + "LEFT OUTER JOIN airports AS found_on_airports ON luggages.location_found_id = found_on_airports.id "
                + "LEFT OUTER JOIN countries AS found_on_airport_countries ON found_on_airports.country_id = found_on_airport_countries.id "
                + "LEFT OUTER JOIN colors AS primary_colors ON luggages.primary_color_id = primary_colors.id "
                + "LEFT OUTER JOIN colors AS secondary_colors ON luggages.secondary_color_id = secondary_colors.id "
                + "LEFT OUTER JOIN passengers ON luggages.passenger_id = passengers.id "
                + "LEFT OUTER JOIN countries AS passenger_countries ON passengers.country_id = passenger_countries.id "
                + "LEFT OUTER JOIN insurance_companies ON luggages.insurance_company_id = insurance_companies.id ";
    }

    /**
     * Marshalls a {@link ResultSet} into a {@link Luggage}.
     *
     * @param rs the resultset
     * @return the parsed {@link Luggage}
     * @throws SQLException
     */
    private Luggage parseResultSet(ResultSet rs) throws SQLException {
        // brand
        int brandId = rs.getInt("brand_id");
        String brandName = rs.getString("brand_name");
        Brand brand = new Brand(brandName, brandId);

        // luggage type
        int luggageTypeId = rs.getInt("luggage_type_id");
        String luggageTypeName = rs.getString("luggage_type_name");
        LuggageType luggageType = new LuggageType(luggageTypeName, luggageTypeId);

        // status
        int statusId = rs.getInt("status_id");
        String statusName = rs.getString("status_name");
        Timestamp date = rs.getTimestamp("status_timestamp");
        Status status = new Status(statusName, statusId, date, null);

        // country
        Integer countryId = rs.getInt("airport_found_country_id");
        String countryCode = rs.getString("airport_found_country_code");
        String countryName = rs.getString("airport_found_country_name");
        Country country = new Country(countryCode, countryName, countryId);

        // airport
        Integer airportId = rs.getInt("airport_found_id");
        String airportName = rs.getString("airport_found_name");
        Integer airportTimezone = rs.getInt("airport_found_timezone");
        Boolean airportDaylightSaving = rs.getBoolean("airport_found_daylight_saving");
        Airport airport = new Airport(airportName, country, airportTimezone, airportDaylightSaving, airportId);

        // passenger country
        Integer passengerCountryId = rs.getInt("passenger_country_id");
        String passengerCountryCode = rs.getString("passenger_country_code");
        String passengerCountryName = rs.getString("passenger_country_name");
        Country passengerCountry = new Country(passengerCountryCode, passengerCountryName, passengerCountryId);

        // passenger
        Integer passengerId = rs.getInt("passenger_id");
        String passengerFirstName = rs.getString("passenger_first_name");
        String passengerMiddleName = rs.getString("passenger_middle_name");
        String passengerLastName = rs.getString("passenger_last_name");
        String passengerAddress = rs.getString("passenger_address");
        String passengerPostcode = rs.getString("passenger_postcode");
        String passengerCity = rs.getString("passenger_city");
        String passengerEmail = rs.getString("passenger_email");
        String passengerPhone = rs.getString("passenger_phone");

        Passenger passenger = new Passenger(
                passengerFirstName,
                passengerMiddleName,
                passengerLastName,
                passengerAddress,
                passengerPostcode,
                passengerCity,
                passengerCountry,
                passengerEmail,
                passengerPhone,
                passengerId);

        // flight
        Integer flightId = rs.getInt("label_flight_id");
        String flightNumber = rs.getString("label_flight_flightnumber");
        Flight flight = new Flight(flightNumber, flightId);

        // label
        Integer labelId = rs.getInt("label_id");
        String labelTag = rs.getString("label_tag");
        Label label = new Label(labelTag, flight, labelId);

        // primary color
        Integer primaryColorId = rs.getInt("primary_color_id");
        Integer primaryRalCode = rs.getInt("primary_color_ral_code");
        String primaryColorName = rs.getString("primary_color_name");
        Color primaryColor = new Color(primaryColorName, primaryRalCode, primaryColorId);

        // secondary color
        Integer secondaryColorId = rs.getInt("secondary_color_id");
        Integer secondaryRalCode = rs.getInt("secondary_color_ral_code");
        String secondaryColorName = rs.getString("secondary_color_name");
        Color secondaryColor = new Color(secondaryColorName, secondaryRalCode, secondaryColorId);

        // insurance company
        Integer insuranceId = rs.getInt("insurance_company_id");
        String insuranceName = rs.getString("insurance_company_name");
        Insurance insurance = new Insurance(insuranceName, insuranceId);

        // luggage
        int luggageId = rs.getInt("luggage_id");
        String luggageRegistrationId = rs.getString("luggage_registration_id");
        Timestamp dateFound = rs.getTimestamp("date_found");
        Integer sizeWidth = rs.getInt("size_width");
        Integer sizeLength = rs.getInt("size_length");
        Integer sizeHeight = rs.getInt("size_height");
        Integer weight = rs.getInt("weight");
        String characteristics = rs.getString("other_characteristics");

        return new Luggage(luggageRegistrationId,
                dateFound,
                airport,
                luggageType,
                brand,
                label,
                status,
                sizeWidth,
                sizeLength,
                sizeHeight,
                weight,
                passenger,
                characteristics,
                primaryColor,
                secondaryColor,
                insurance,
                luggageId
        );
    }
}
