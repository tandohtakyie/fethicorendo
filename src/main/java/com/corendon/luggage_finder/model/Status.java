/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.model;

import com.corendon.luggage_finder.implementables.DatabaseEntry;

import java.util.Date;

/**
 * This class represents a entry from the table 'Statuses'.
 *
 * @author Dylan Tweebeeke
 */
public class Status extends DatabaseEntry {

    //making attributes
    private String name;

    /**
     * Constructor for status
     *
     * @param name
     */
    public Status(String name) {
        this(name, null);
    }

    /**
     * Constructor for status
     *
     * @param name
     * @param id
     */
    public Status(String name, Integer id) {
        this(name, id, null, null);
    }

    /**
     * Constructor for status
     *
     * @param name
     * @param id
     * @param created
     * @param modified
     */
    public Status(String name, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
    }

    /**
     * returns the name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
