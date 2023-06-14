package com.example.imtrying.Models;

public class Schedule {

    private String dataMorning;
    private String dataMidDay;
    private String dataEvening;

    public Schedule() {}

    public Schedule(String dataMorning, String dataMidDay, String dataEvening) {
        this.dataMorning = dataMorning;
        this.dataMidDay = dataMidDay;
        this.dataEvening = dataEvening;
    }

    public String getDataMorning() {
        return dataMorning;
    }

    public void setDataMorning(String dataMorning) {
        this.dataMorning = dataMorning;
    }

    public String getDataMidDay() {
        return dataMidDay;
    }

    public void setDataMidDay(String dataMidDay) {
        this.dataMidDay = dataMidDay;
    }

    public String getDataEvening() {
        return dataEvening;
    }

    public void setDataEvening(String dataEvening) {
        this.dataEvening = dataEvening;
    }
}
