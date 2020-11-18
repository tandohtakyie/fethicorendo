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
 * An entry from the "functions" table.
 *
 * @author Petar Dimitrov
 */
public class Function extends DatabaseEntry {

    private String name;

    public Function(String name) {
        this(name, null);
    }

    /**
     * Create minimal entry.
     *
     * @param name Name of function.
     * @param id Entry id.
     */
    public Function(String name, Integer id) {
        this(name, id, null, null);
    }

    /**
     * Create full entry.
     *
     * @param name Name of fucntion.
     * @param id Entry id.
     * @param created Created timestamp.
     * @param modified Modified timestamp.
     */
    public Function(String name, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
    }

    /**
     * Get function name.
     *
     * @return Function name as string.
     */
    public String getName() {
        return name;
    }

    /**
     * Set function name.
     *
     * @param name New function name as string.
     */
    public void setName(String name) {
        this.name = name;
    }
}
