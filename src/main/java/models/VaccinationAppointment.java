package models;

public class VaccinationAppointment {

    private String date;
    private String time;
    private String vaccinationType;
    private int batchNumber;
    private String vaccinatorName;
    private Patient patient;

    public VaccinationAppointment(String date, String time, String vaccinationType, int batchNumber, String vaccinatorName, Patient patient) {
        this.date = date;
        this.time = time;
        this.vaccinationType = vaccinationType;
        this.batchNumber = batchNumber;
        this.vaccinatorName = vaccinatorName;
        this.patient = patient;
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

    public String getVaccinatorName() {
        return vaccinatorName;
    }

    public void setVaccinatorName(String vaccinatorName) {
        this.vaccinatorName = vaccinatorName;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time + ", Vaccination Type: " + vaccinationType + ", Batch No: " + batchNumber + ", Medical Personal: " + vaccinatorName + ", Patient: " + patient.getPPSN();
    }
}
