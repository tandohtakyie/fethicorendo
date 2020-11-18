/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.implementables;

import java.util.Date;

/**
 * This class represents all entries inside the database and should be extended
 * by all classes that represent a entry.
 *
 * @author Tom J. Wassing
 */
public abstract class DatabaseEntry {

    private Integer id;
    private Date created;
    private Date modified;

    /**
     * Default cosntructor
     */
    public DatabaseEntry() {
        this.id = null;
        this.created = null;
        this.modified = null;
    }

    /**
     * Cosnstructor with only the surrogate key
     *
     * @param id the surrogate key
     */
    public DatabaseEntry(Integer id) {
        this.id = id;
        this.created = null;
        this.modified = null;
    }

    /**
     * Construcotr with all parameters
     *
     * @param id the surrogate key
     * @param created the created date
     * @param modified the last modified date
     */
    public DatabaseEntry(Integer id, Date created, Date modified) {
        this.id = id;
        this.created = created;
        this.modified = modified;
    }

    /**
     * Returns the surrogate key
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the new surrgate key/id
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the created date.
     *
     * @return the created date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Returns the last modified date.
     *
     * @return the last modified date
     */
    public Date getModified() {
        return modified;
    }

}
