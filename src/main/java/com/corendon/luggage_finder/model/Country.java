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
 * This class represents a entry from the table 'countries'.
 *
 * @author Petar Dimitrov
 */
public class Country extends DatabaseEntry {

    private String name;
    private String code;

    public Country(String code, String name) {
        this(code, name, null);
    }

    public Country(String code, String name, Integer id) {
        this(code, name, id, null, null);
    }

    public Country(String code, String name, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
        this.code = code;
    }

    /**
     * Set the name of the entry.
     *
     * @param name Name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the entry.
     *
     * @return This entry's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the country code.
     *
     * @param code Code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get country code.
     *
     * @return The country code.
     */
    public String getCode() {
        return code;
    }
}
