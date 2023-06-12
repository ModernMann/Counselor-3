package com.example.imtrying.Models;

public class DataClassGame {

    private String dataTitle;
    private String dataDesc;
    private String dataTime;
    private String dataType;
    private String dataYear;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataYear() {
        return dataYear;
    }

    public DataClassGame(String dataTitle, String dataDesc, String dataTime, String dataType, String dataYear) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataTime = dataTime;
        this.dataType = dataType;
        this.dataYear = dataYear;
    }
    public DataClassGame(){

    }
}