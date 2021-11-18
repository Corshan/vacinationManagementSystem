package models;

import Utils.CorList;
import Utils.Utilities;

public class VaccinationBooth {

    private String identifier;
    private int floorLevel;
    private String accessibility;
    private CorList<VaccinationAppointment> appointments;

    public VaccinationBooth(String identifier, int floorLevel, String accessibility) {
        if (Utilities.validBoothIdentifier(identifier)) {
            this.identifier = identifier;
        } else {
            this.identifier = "XX";
        }
        if (Utilities.validIntPositive(floorLevel)) {
            this.floorLevel = floorLevel;
        } else {
            this.floorLevel = -1;
        }
        this.accessibility = accessibility;
        this.appointments = new CorList<>();
    }

    public boolean checkAppointment(String date, String time) {
        boolean appointmentCheck = false;
        if (!appointments.isEmpty()) {
            for (VaccinationAppointment appointment : appointments) {
                if (appointment.getDate().equals(date) && appointment.getTime().equals(time)) {
                    appointmentCheck = true;
                }
            }
        }
        return appointmentCheck;
    }

    public void addAppointment(VaccinationAppointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(VaccinationAppointment appointment){
        appointments.remove(appointment);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        if (Utilities.validBoothIdentifier(identifier)) {
            this.identifier = identifier;
        }
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        if (Utilities.validIntPositive(floorLevel)) {
            this.floorLevel = floorLevel;
        }
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public CorList<VaccinationAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(CorList<VaccinationAppointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Identifier: " + identifier + ", FloorLevel: " + floorLevel + ", Accessibility: " + accessibility;
    }
}
