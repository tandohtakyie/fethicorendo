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
 * This class represents a entry from the table 'colors'.
 *
 * @author Tom J. Wassing
 */
public class Color extends DatabaseEntry {

    private String name;
    private int ralCode;

    public Color(String name, int ralCode) {
        this(name, ralCode, null);
    }

    public Color(String name, int ralCode, Integer id) {
        this(name, ralCode, id, null, null);
    }

    public Color(String name, int ralCode, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
        this.ralCode = ralCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRalCode() {
        return ralCode;
    }

    public void setRalCode(int ralCode) {
        this.ralCode = ralCode;
    }

}
