package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationBoothTest {

    VaccinationBooth booth;
    VaccinationAppointment appointment1, appointment2;
    Patient patient1, patient2;

    @BeforeEach
    void setUp() {
        booth = new VaccinationBooth("A2", 2, "None");
        patient1 = new Patient("1234567EE", "Corey Shanahan", "1-2-2002", "Waterford", "0556849", "@email.com", "none");
        patient2 = new Patient("1234567XX", "Cori Shanahan", "1-3-2002", "Waterford", "0556849", "@email.com", "none");
        appointment1 = new VaccinationAppointment("1-1-2021", "9:00", "Maderna", 34523, "Nurse Macey", patient1);
        appointment2 = new VaccinationAppointment("1-1-2021", "9:10", "Maderina", 126354, "Nurse Tracey", patient2);

        booth.getAppointments().add(appointment1);

    }

    @AfterEach
    void tearDown() {
        booth = null;
        patient1 = null;
        patient2 = null;
        appointment1 = null;
        appointment2 = null;
    }

    @Test
    void checkAppointment() {
        assertFalse(booth.checkAppointment(appointment2.getDate(), appointment2.getTime()));
        appointment2.setDate("1-1-2021");
        appointment2.setTime("9:00");
        assertTrue(booth.checkAppointment(appointment2.getDate(), appointment2.getTime()));
    }
}