package com.example.imtrying;

public class DataClassSchedule {
    private String dataMorning;
    private String dataMidDay;
    private String dataEvening;

    public String getDataMorning() {
        return dataMorning;
    }

    public String getDataMidDay() {
        return dataMidDay;
    }

    public String getDataEvening() {
        return dataEvening;
    }

    public DataClassSchedule(String dataMorning, String dataMidDay, String dataEvening) {
        this.dataMorning = dataMorning;
        this.dataMidDay = dataMidDay;
        this.dataEvening = dataEvening;
    }
    public DataClassSchedule() {

    }
}
