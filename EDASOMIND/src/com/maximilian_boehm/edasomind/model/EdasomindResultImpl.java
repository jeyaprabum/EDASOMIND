package com.maximilian_boehm.edasomind.model;

import java.util.Calendar;

import com.maximilian_boehm.edasomind.access.struct.EdasomindResult;
import com.maximilian_boehm.edasomind.access.struct.EdasomindSignificance;

public class EdasomindResultImpl implements EdasomindResult{

    private EdasomindSignificance Signifacance;
    private Calendar CalendarFrom;
    private Calendar CalenderTo;
    private String Message;

    @Override
    public EdasomindSignificance getSignificance() {
        return Signifacance;
    }
    public void setSignificance(EdasomindSignificance signifacance) {
        Signifacance = signifacance;
    }
    @Override
    public Calendar getCalendarFrom() {
        return CalendarFrom;
    }
    public void setCalendarFrom(Calendar calendarFrom) {
        CalendarFrom = calendarFrom;
    }
    @Override
    public Calendar getCalenderTo() {
        return CalenderTo;
    }
    public void setCalenderTo(Calendar calenderTo) {
        CalenderTo = calenderTo;
    }
    @Override
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }

}
