package models;

import Utils.CorList;

public class VaccinationCentre {

    private String name;
    private String address;
    private String eircode;
    private int parkingSpaces;
    private CorList<VaccinationBooth> booths;

    public VaccinationCentre(String name, String address, String eircode, int parkingSpaces) {
        this.name = name;
        this.address = address;
        this.eircode = eircode.toUpperCase();
        this.parkingSpaces = parkingSpaces;
        booths = new CorList<>();
    }

    public void addBooth(VaccinationBooth booth){
        booths.add(booth);
    }

    public void removeBooth(VaccinationBooth booth){
        booths.remove(booth);
    }

    public boolean isInCentre(VaccinationBooth vaccinationBooth){
        for (VaccinationBooth booth : booths){
            if (vaccinationBooth.getIdentifier().equals(booth.getIdentifier())){
                return true;
            }
        }
        return false;
    }

    public boolean checkAppointment(String date, String time, VaccinationBooth booth){
        return booth.checkAppointment(date, time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public CorList<VaccinationBooth> getBooths() {
        return booths;
    }

    public void setBooths(CorList<VaccinationBooth> booths) {
        this.booths = booths;
    }

    public String toString(){
        return "Centre Name: " + name + ", Address: " + address + ", Eircode: " + eircode + ", Parking Spaces: " + parkingSpaces;
    }
}
