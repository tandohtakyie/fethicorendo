/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corendon.luggage_finder.model;

import com.corendon.luggage_finder.implementables.DatabaseEntry;

import java.util.Date;

/**
 * This class represents a entry from the table 'StatusesHistory'.
 *
 * @author Dylan Tweebeeke
 */
public class StatusHistory extends DatabaseEntry {

    private int luggageId;
    private Status status;

    /**
     * Constructor for Status history
     *
     * @param luggageId
     * @param status
     */
    public StatusHistory(int luggageId, Status status) {
        this(luggageId, status, null);
    }

    /**
     * Constructor for Status history
     *
     * @param luggageId
     * @param status
     * @param created
     */
    public StatusHistory(int luggageId, Status status, Date created) {
        super(null, created, created);
        this.luggageId = luggageId;
        this.status = status;
    }

    /**
     * Gets the luggageID
     *
     * @return luggageId
     */
    public int getLuggageId() {
        return luggageId;
    }

    /**
     * Sets the luggageid
     *
     * @param luggageId
     */
    public void setLuggageId(int luggageId) {
        this.luggageId = luggageId;
    }

    /**
     * gets the status
     *
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * sets the status
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
