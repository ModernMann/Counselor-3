package com.example.imtrying.Models;

public class DataClass {
    private String dataTitle;
    private String dataDesc;
    private String dataTime;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataTime() {
        return dataTime;
    }

    public DataClass(String dataTitle, String dataDesc, String dataTime) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataTime = dataTime;
    }

    public DataClass(){

    }
}