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
 *
 * @author Tom J. Wassing
 */
public class LuggageType extends DatabaseEntry {

    private String name;

    public LuggageType(String name) {
        this(name, null);
    }

    public LuggageType(String name, Integer id) {
        this(name, id, null, null);
    }

    public LuggageType(String name, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
