/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.implementables;

import com.corendon.luggage_finder.database.JDBC;

import java.util.List;

/**
 * This class represents a table inside the database with bais CRUD operations.
 * All classes that represents a table from the database have to extends this
 * class.
 *
 * @author Tom J. Wassing
 */
public abstract class DatabaseTable<T extends DatabaseEntry> {

    private JDBC jdbc;

    /**
     * Default constuctor
     */
    public DatabaseTable() {
        this.jdbc = JDBC.getInstance();
    }

    /**
     * Returns the JDBC driver.
     *
     * @return the JDBC driver
     */
    protected JDBC getJdbc() {
        return jdbc;
    }

    /**
     * This method should return all entries from the table with a specified
     * maximum of entries to be returned.
     *
     * @param size the maximum of entries to be be returned
     * @return the entries
     */
    public abstract List<T> getAll(int size);

    /**
     * This method should implemented a INSERT query to the corresponding table.
     *
     * @param entry the entry to be updated
     * @return if the query succeeded
     */
    public abstract boolean insert(T entry);

    /**
     * This method should implemented a UPDATE query to the corresponding table.
     *
     * @param entry the entry to be inserted
     * @return if the query succeeded
     */
    public abstract boolean update(T entry);

    /**
     * This method should implemented a DELETE query to the corresponding table.
     *
     * @param entry the entry to be deleted
     * @return if the query succeeded
     */
    public abstract boolean delete(T entry);
}
