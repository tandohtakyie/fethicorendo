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
 * This class represents a entry from the table 'brands'.
 *
 * @author Dylan Tweebeeke
 */
public class Brand extends DatabaseEntry {

    private String name;

    /**
     * constructor of the luggage brand
     *
     * @param name
     */
    public Brand(String name) {
        this(name, null);
    }

    /**
     * constructor of the luggage brand
     *
     * @param name
     * @param id
     */
    public Brand(String name, Integer id) {
        this(name, id, null, null);
    }

    /**
     * constructor of the luggage brand
     *
     * @param name
     * @param created
     * @param id
     * @param modified
     */
    public Brand(String name, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
    }

    /**
     * gets the name
     *
     * @return name
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
