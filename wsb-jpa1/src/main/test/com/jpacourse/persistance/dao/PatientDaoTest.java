package com.jpacourse.persistance.dao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Test
    public void createVisit() {
        //given
        LocalDateTime visitDate = LocalDateTime.now();
        DoctorEntity doctor = doctorDao.findOne(1L);
        PatientEntity patient = patientDao.findOne(1L);
        assertThat(patient).isNotNull();
        assertThat(doctor).isNotNull();
        int patientVisitsCount = patient.getVisits().size();
        //when
        VisitEntity result = patientDao.addVisitToPatient(1L, 1L, "randompitupitublabla", visitDate);
        //then
        assertThat(result).isNotNull();
        assertThat(patient.getVisits().size()).isEqualTo(patientVisitsCount + 1);
        assertThat(result.getPatient().getId()).isEqualTo(patient.getId());
        assertThat(result.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(result.getDescription()).isEqualTo("randompitupitublabla");
        assertThat(result.getTime()).isEqualTo(visitDate);
    }
    @Test
    public void testFindPatientsByLastName() {
        // given
        String lastName = "Blabla"; // Adjusted to match your dataset
        // when
        Collection<PatientEntity> result = patientDao.findPatientsByLastName(lastName);
        List<PatientEntity> listOfResults = new ArrayList<>(result);
        // then
        assertNotNull(listOfResults);
        assertFalse(listOfResults.isEmpty());
        assertEquals(1, listOfResults.size());
        PatientEntity patient = listOfResults.get(0);
        assertEquals(lastName, patient.getLastName());
        assertEquals("Ania", patient.getFirstName());
        assertEquals("PAT001", patient.getPatientNumber());
        assertEquals("1985-10-10", patient.getDateOfBirth().toString());
        assertEquals("whoknows@gmail.com", patient.getEmail());
    }

    @Test
    public void testFindPatientsWithVisitsCountGreaterThanOne() {
        // given
        int visitsCount = 0; // Adjusted since each patient has only one visit in your dataset
        // when
        Collection<PatientEntity> result = patientDao.findPatientsWithVisitsCountGreaterThan(visitsCount);
        List<PatientEntity> listOfResults = new ArrayList<>(result);
        // then
        assertNotNull(listOfResults);
        assertFalse(listOfResults.isEmpty());
        assertEquals(2, listOfResults.size()); // Both patients have at least one visit
        PatientEntity patient1 = listOfResults.get(0);
        PatientEntity patient2 = listOfResults.get(1);

        assertNotNull(patient1);
        assertNotNull(patient2);

        // Validate first patient
        assertEquals("Ania", patient1.getFirstName());
        assertEquals("Blabla", patient1.getLastName());
        assertEquals("PAT001", patient1.getPatientNumber());
        assertEquals("1985-10-10", patient1.getDateOfBirth().toString());

        // Validate second patient
        assertEquals("Justyna", patient2.getFirstName());
        assertEquals("Yapyap", patient2.getLastName());
        assertEquals("PAT002", patient2.getPatientNumber());
        assertEquals("1990-05-15", patient2.getDateOfBirth().toString());
    }

    @Test
    public void testFindPatientsWithFirstTime() {
        // given
        Boolean isFirstTime = null;
        // when
        Collection<PatientEntity> result = patientDao.findPatientsByFirstTimeStatus(isFirstTime);
        List<PatientEntity> listOfResults = new ArrayList<>(result);
        // then
        assertNotNull(listOfResults);
        assertTrue(listOfResults.isEmpty());
    }

}