/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.database.tables;

import com.corendon.luggage_finder.implementables.DatabaseTable;
import com.corendon.luggage_finder.model.Country;
import com.corendon.luggage_finder.model.Function;
import com.corendon.luggage_finder.model.User;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the table "users" in the database.
 *
 * @author Petar Dimitrov
 */
public class UsersTable extends DatabaseTable<User> {

    /**
     * Login to the application.
     *
     * @param username User's username.
     * @param password User's password.
     * @return {@link User} object of the logged in user, on successful login.
     * On failed login returns null.
     */
    public User login(String username, String password) {
        String query = "SELECT users.id, username, first_name, IFNULL(middle_name, '') AS middle_name, last_name, birth_date, email, "
                + "functions.id AS function_id, functions.name AS function_name, "
                + "employment_date, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name, "
                + "password, image, reset_password "
                + "FROM users "
                + "INNER JOIN functions ON users.function_id = functions.id "
                + "INNER JOIN countries ON users.country_id = countries.id "
                + "WHERE username = ? AND password = ?;";

        try (ResultSet rs = getJdbc().executeSelectQuery(query, username, password)) {
            if (rs != null && rs.next()) {
                String usrname = rs.getString("username");
                String passw = rs.getString("password");

                if (usrname.matches(username) && passw.matches(password)) {
                    int functionId = rs.getInt("function_id");
                    String functionName = rs.getString("function_name");

                    Function function = new Function(functionName, functionId);

                    int countryId = rs.getInt("country_id");
                    String countryCode = rs.getString("country_code");
                    String countryName = rs.getString("country_name");

                    Country country = new Country(countryCode, countryName, countryId);

                    int userId = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String middleName = rs.getString("middle_name");
                    String lastName = rs.getString("last_name");
                    Date birthDate = rs.getDate("birth_date");
                    String email = rs.getString("email");
                    Date recruitment = rs.getDate("employment_date");
                    InputStream image = rs.getBinaryStream("image");
                    boolean resetPassword = rs.getBoolean("reset_password");

                    User user = new User(usrname, firstName, middleName, lastName,
                            birthDate, email, function, recruitment, country,
                            passw, image, resetPassword, userId);

                    return user;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * search users table for a string.
     *
     * @param searchString String to search for.
     * @return List of {@link User} results.
     */
    public List<User> search(String searchString) {
        String query = "SELECT users.id, username, first_name, IFNULL(middle_name, '') AS middle_name, last_name, birth_date, email, "
                + "functions.id AS function_id, functions.name AS function_name, "
                + "employment_date, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name, "
                + "password, image, reset_password "
                + "FROM users "
                + "INNER JOIN functions ON users.function_id = functions.id "
                + "INNER JOIN countries ON users.country_id = countries.id "
                + "WHERE first_name LIKE ? OR "
                + "middle_name LIKE ? OR "
                + "last_name LIKE ? OR "
                + "birth_date LIKE ? OR "
                + "email LIKE ? OR "
                + "functions.name LIKE ? OR "
                + "employment_date LIKE ? OR "
                + "countries.name LIKE ?;";

        List<User> users = new ArrayList<>();

        searchString += "%";

        try (ResultSet rs = getJdbc().executeSelectQuery(query, searchString, searchString, searchString, searchString, searchString, searchString, searchString, searchString)) {
            while (rs != null && rs.next()) {
                int functionId = rs.getInt("function_id");
                String functionName = rs.getString("function_name");

                Function function = new Function(functionName, functionId);

                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryCode, countryName, countryId);

                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                Date birthDate = rs.getDate("birth_date");
                String email = rs.getString("email");
                Date recruitment = rs.getDate("employment_date");
                String password = rs.getString("password");
                InputStream image = rs.getBinaryStream("image");
                boolean resetPassword = rs.getBoolean("reset_password");

                User user = new User(username, firstName, middleName, lastName,
                        birthDate, email, function, recruitment, country,
                        password, image, resetPassword, userId);

                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    /**
     * Get all data from users table.
     *
     * @param size Return size limit.
     * @return List of User entries.
     */
    @Override
    public List<User> getAll(int size) {
        String query = "SELECT users.id, username, first_name, IFNULL(middle_name, '') AS middle_name, last_name, birth_date, email, "
                + "functions.id AS function_id, functions.name AS function_name, "
                + "employment_date, "
                + "countries.id AS country_id, countries.code AS country_code, countries.name AS country_name, "
                + "password, image, reset_password "
                + "FROM users "
                + "INNER JOIN functions ON users.function_id = functions.id "
                + "INNER JOIN countries ON users.country_id = countries.id "
                + "LIMIT ?;";

        List<User> users = new ArrayList<>();

        try (ResultSet rs = getJdbc().executeSelectQuery(query, size)) {
            while (rs != null && rs.next()) {
                int functionId = rs.getInt("function_id");
                String functionName = rs.getString("function_name");

                Function function = new Function(functionName, functionId);

                int countryId = rs.getInt("country_id");
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                Country country = new Country(countryCode, countryName, countryId);

                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                Date birthDate = rs.getDate("birth_date");
                String email = rs.getString("email");
                Date recruitment = rs.getDate("employment_date");
                String password = rs.getString("password");
                InputStream image = rs.getBinaryStream("image");
                boolean resetPassword = rs.getBoolean("reset_password");

                User user = new User(username, firstName, middleName, lastName,
                        birthDate, email, function, recruitment, country,
                        password, image, resetPassword, userId);

                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    /**
     * Insert entry into table.
     *
     * @param entry User entry to insert.
     * @return Success.
     */
    @Override
    public boolean insert(User entry) {
        String query = "INSERT INTO users ("
                + "username, first_name, middle_name, last_name, birth_date, email, "
                + "function_id, employment_date, country_id, password, image, reset_password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return getJdbc().executeUpdateQuery(query,
                entry.getUsername(),
                entry.getFirstName(),
                entry.getNamePreposition(),
                entry.getSurname(),
                entry.getBirthDate(),
                entry.getEmail(),
                entry.getFunction().getId(),
                entry.getEmploymentDate(),
                entry.getDepartmentLocation().getId(),
                entry.getPassword(),
                entry.getImage(),
                entry.isResetPassword()
        );
    }

    /**
     * Update entry in table.
     *
     * @param entry User entry to update.
     * @return Success.
     */
    @Override
    public boolean update(User entry) {
        String query = "UPDATE users SET username = ?, first_name = ?, middle_name = ?, "
                + "last_name = ?, birth_date = ?, email = ?, function_id = ?, employment_date = ?,"
                + "country_id = ?, password = ?, image = ?, reset_password = ? WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query,
                entry.getUsername(),
                entry.getFirstName(),
                entry.getNamePreposition(),
                entry.getSurname(),
                entry.getBirthDate(),
                entry.getEmail(),
                entry.getFunction().getId(),
                entry.getEmploymentDate(),
                entry.getDepartmentLocation().getId(),
                entry.getPassword(),
                entry.getImage(),
                entry.isResetPassword(),
                entry.getId()
        );
    }

    /**
     * Delete entry from table.
     *
     * @param entry User entry to delete.
     * @return Success.
     */
    @Override
    public boolean delete(User entry) {
        String query = "DELETE FROM users WHERE id = ?;";

        return getJdbc().executeUpdateQuery(query, entry.getId());
    }
}
