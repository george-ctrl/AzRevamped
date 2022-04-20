package de.swm;

import java.sql.Time;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeModel {
    private int userId;
    private LocalTime timeStart;

    private LocalTime timeEnd;
    private double timeWorkedMinutes;

    public void calcTimeWorked(String timeBeg, String timeEnd, int userId) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime timeStart = LocalTime.parse(timeBeg, formatter);
        LocalTime timeDone = LocalTime.parse(timeEnd, formatter);
        double elapsedMinutes = Duration.between(timeStart, timeDone).toMinutes();

        if(elapsedMinutes >= 600){
            elapsedMinutes = elapsedMinutes - 45;
        }
        else if(elapsedMinutes >= 480){
            elapsedMinutes  = elapsedMinutes - 30;
        }
        this.timeStart = timeStart.truncatedTo(ChronoUnit.MINUTES);
        this.timeEnd = timeDone.truncatedTo(ChronoUnit.MINUTES);
        this.timeWorkedMinutes = elapsedMinutes;
        this.userId = userId;
    }

    public TimeModel(LocalTime timeStart, LocalTime timeEnd, int userId) throws ParseException {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.userId = userId;
        calcTimeWorked(timeStart.toString(), timeEnd.toString(),userId);
    }

    public TimeModel(){

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public double getTimeWorkedMinutes() {
        return timeWorkedMinutes;
    }

    public void setTimeWorkedMinutes(double timeWorkedMinutes) {
        this.timeWorkedMinutes = timeWorkedMinutes;
    }
}
