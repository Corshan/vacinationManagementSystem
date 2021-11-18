package models;

import java.util.Date;

public class VaccinationRecord {

    private String date;
    private String time;
    private String vaccinationType;
    private int batchNumber;

    public VaccinationRecord(String date, String time, String vaccinationType, int batchNumber) {
        this.date = date;
        this.time = time;
        this.vaccinationType = vaccinationType;
        this.batchNumber = batchNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVaccinationType() {
        return vaccinationType;
    }

    public void setVaccinationType(String vaccinationType) {
        this.vaccinationType = vaccinationType;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String toString(){
        return "Date: " + date + ", Time: " + time + ", Vaccine Type: " + vaccinationType + ", Batch No: " + batchNumber;
    }
}
