package controllers;

import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationAPITest {

    VaccinationAPI vaccinationAPI;
    VaccinationCentre centre;
    VaccinationBooth booth1, booth2, booth3;
    VaccinationAppointment appointment1, appointment2, appointment3;
    Patient patient1, patient2, patient3;
    VaccinationRecord record1, record2;

    @BeforeEach
    void setUp() {
        vaccinationAPI = new VaccinationAPI();
        centre = new VaccinationCentre("Wit", "asd", "X91 4f04", 100);
        booth1 = new VaccinationBooth("1A", 2, "none");
        booth2 = new VaccinationBooth("1B", 2, "none");
        booth3 = new VaccinationBooth("1C", 2, "none");

        patient1 = new Patient("1234567EE", "Corey Shanahan", "1-2-2002", "Waterford", "0556849", "@email.com", "none");
        patient2 = new Patient("1234567XX", "Cori Shanahan", "1-3-2002", "Waterford", "0556849", "@email.com", "none");
        patient3 = new Patient("1234567PP", "Corrie Shanahan", "1-3-2002", "Waterford", "0556849", "@email.com", "none");
        appointment1 = new VaccinationAppointment("1-1-2021", "9:00", "Maderna", 34523, "Nurse Macey", patient1);
        appointment2 = new VaccinationAppointment("1-1-2021", "9:10", "Maderina", 126354, "Nurse Tracey", patient2);
        appointment3 = new VaccinationAppointment("1-1-2021", "9:10", "Maderina", 126354, "Nurse Tracey", patient3);

        record1 = new VaccinationRecord("1-1-2021", "9:10", "Maderina", 126354);
        record2 = new VaccinationRecord("1-1-2021", "9:00", "Maderna", 34523);

        vaccinationAPI.addVaccinationCentre(centre);
        centre.addBooth(booth1);
        centre.addBooth(booth2);
        booth1.addAppointment(appointment1);
        booth1.addAppointment(appointment2);
        booth2.addAppointment(appointment3);

        vaccinationAPI.addPatient(patient1);
        vaccinationAPI.addPatient(patient2);

        patient1.getRecords().add(record1);
        patient1.getRecords().add(record2);
    }

    @AfterEach
    void tearDown() {
        centre = null;
        booth1 = null;
        booth2 = null;
        patient1 = null;
        patient2 = null;
        patient3 = null;
        appointment1 = null;
        appointment2 = null;
        appointment3 = null;
    }

    @Test
    void removeBooth() {
        assertFalse(vaccinationAPI.removeBooth(booth1));
        assertTrue(centre.isInCentre(booth1));
        assertTrue(booth1.checkAppointment(appointment2.getDate(), appointment2.getTime()));
        centre.addBooth(booth3);
        assertTrue(vaccinationAPI.removeBooth(booth1));
        assertFalse(centre.isInCentre(booth1));
    }

    @Test
    void addVaccinationBooth(){
        assertTrue(vaccinationAPI.addVaccinationBooth(centre, booth1));
        assertTrue(vaccinationAPI.addVaccinationBooth(centre, booth2));
        assertFalse(vaccinationAPI.addVaccinationBooth(centre, booth3));
    }

    @Test
    void findAPatient(){
        assertEquals(patient1, vaccinationAPI.findPatient("1234567EE"));
        assertEquals(patient1, vaccinationAPI.findPatient("1234567ee"));
        assertEquals(patient2,vaccinationAPI.findPatient("1234567XX"));
        assertNull(vaccinationAPI.findPatient("45367AA"));
    }

    @Test
    void clear(){
        assertEquals(patient1, vaccinationAPI.getPatients().get(0));
        assertEquals(centre, vaccinationAPI.getVaccinationCentres().get(0));
        vaccinationAPI.clear();
        assertNull(vaccinationAPI.getVaccinationCentres().get(0));
        assertNull(vaccinationAPI.getVaccinationCentres().get(0));
    }

    @Test
    void removePatientRecords(){
        assertEquals(record1, patient1.getRecords().get(0));
        assertEquals(record2, patient1.getRecords().get(1));
        vaccinationAPI.removePatientRecord(record1);
        vaccinationAPI.removePatientRecord(record2);
        assertNull(patient1.getRecords().get(0));
        assertNull(patient1.getRecords().get(1));
    }
}