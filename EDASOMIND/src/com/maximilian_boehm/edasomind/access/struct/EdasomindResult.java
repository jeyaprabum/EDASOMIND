package com.maximilian_boehm.edasomind.access.struct;

import java.util.Calendar;

public interface EdasomindResult {


    public EdasomindSignificance getSignificance();
    public Calendar getCalendarFrom();
    public Calendar getCalenderTo();
    public String getMessage();

}
