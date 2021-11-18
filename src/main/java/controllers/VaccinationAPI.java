package controllers;

import models.*;
import Utils.CorList;

public class VaccinationAPI {
    private CorList<VaccinationCentre> vaccinationCentres;
    private CorList<Patient> patients;

    public VaccinationAPI() {
        vaccinationCentres = new CorList<>();
        patients = new CorList<>();
    }

    public void addVaccinationCentre(VaccinationCentre centre) {
        vaccinationCentres.add(centre);
    }

    public boolean addVaccinationBooth(VaccinationCentre centre, VaccinationBooth booth) {
        if (!centre.isInCentre(booth)) {
            centre.addBooth(booth);
            return false;
        } else {
            return true;
        }
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient findPatient(String PPSN) {
        for (Patient patient : patients) {
            if (patient.getPPSN().equals(PPSN.toUpperCase())) {
                return patient;
            }
        }
        return null;
    }

    public void removeCentre(VaccinationCentre centre) {
        vaccinationCentres.remove(centre);
    }

    public boolean removeBooth(VaccinationBooth booth) {
        VaccinationCentre centre = null;
        for (VaccinationCentre vaccinationCentre : vaccinationCentres) {
            if (vaccinationCentre.isInCentre(booth)) {
                centre = vaccinationCentre;
            }
        }

        if (!booth.getAppointments().isEmpty()) {
            for (VaccinationBooth vaccinationBooth : centre.getBooths()) {
                if (!vaccinationBooth.equals(booth)) {
                    for (VaccinationAppointment appointment : booth.getAppointments()) {
                        if (!vaccinationBooth.checkAppointment(appointment.getDate(), appointment.getTime())) {
                            vaccinationBooth.addAppointment(appointment);
                            booth.removeAppointment(appointment);
                        }
                    }
                }
            }
            if (!booth.getAppointments().isEmpty()) return false;
        }
        centre.removeBooth(booth);
        return true;
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public void removePatientRecord(VaccinationRecord deleteRecord) {
        for (Patient patient : patients) {
            if (patient.getRecords().remove(deleteRecord)) return;
        }
    }

    public CorList<VaccinationCentre> getVaccinationCentres() {
        return vaccinationCentres;
    }

    public void setVaccinationCentres(CorList<VaccinationCentre> vaccinationCentres) {
        this.vaccinationCentres = vaccinationCentres;
    }

    public CorList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(CorList<Patient> patients) {
        this.patients = patients;
    }

    public void clear() {
        vaccinationCentres.clear();
        patients.clear();
    }
}
