package models;

import Utils.CorList;

public class Patient {

    private String PPSN;
    private String name;
    private String dateOfBirth;
    private String address;
    private String phoneNum;
    private String email;
    private String accessibility;
    private CorList<VaccinationRecord> records;

    public Patient(String PPSN, String name, String dateOfBirth, String address, String phoneNum, String email, String accessibility) {
        this.PPSN = PPSN.toUpperCase();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.accessibility = accessibility;
        this.records = new CorList<>();
    }

    public String getPPSN() {
        return PPSN;
    }

    public void setPPSN(String PPSN) {
        this.PPSN = PPSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public CorList<VaccinationRecord> getRecords() {
        return records;
    }

    public void setRecords(CorList<VaccinationRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PPSN: " + PPSN + ", Name: " + name + ", Date Of Birth: " + dateOfBirth + ", Address: " + address + ", Phone No: " + phoneNum + ", Email: " + email + ", Accessibility: " + accessibility;
    }
}
