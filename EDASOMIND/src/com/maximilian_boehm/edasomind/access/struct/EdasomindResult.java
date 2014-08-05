package com.maximilian_boehm.edasomind.access.struct;

import java.util.Calendar;

/**
 * Result-Interface for GUI containing necessary information without logic
 */
public interface EdasomindResult {

    /**
     * @return get relevance of result
     */
    public EdasomindSignificance getSignificance();

    /**
     * getCalenderTo & getCalendarFrom are the Dates between the change occoured
     * @return
     */
    public Calendar getCalendarFrom();

    /**
     * getCalenderTo & getCalendarFrom are the Dates between the change occoured
     * @return
     */
    public Calendar getCalendarTo();

    /**
     * The message what happened in this result
     * @return
     */
    public String getMessage();

}
