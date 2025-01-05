package com.jpacourse.service;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private AddressDao addressDao;
    @Test
    public void testShouldDeletePatientAndHisVisits() {
        // given
        PatientTO patientStateBefore = patientService.getPatientById(1L);
        assertThat(patientStateBefore).isNotNull();
        assertThat(visitDao.findByPatient(1L)).isNotEmpty();
        int doctorCount = doctorDao.findAll().size();
        //when
        patientService.deletePatient(1L);
        // then
        PatientTO patientAfter = patientService.getPatientById(1L);
        assertThat(patientAfter).isNull();
        assertThat(visitDao.findByPatient(1L)).isEmpty();
        assertThat(doctorDao.findAll().size()).isEqualTo(doctorCount);
    }
    @Test
    public void testFindPatientByID_ReturnsCorrectStructure() {
        // when
        PatientTO patient = patientService.getPatientById(1L);
        // then
        assertThat(patient).isNotNull();
        assertThat(patient.getFirstName()).isEqualTo("Ania");
        assertThat(patient.getLastName()).isEqualTo("Blabla");
        assertThat(patient.getPatientNumber()).isEqualTo("PAT001");
        assertThat(patient.getEmail()).isEqualTo("whoknows@gmail.com");
        assertThat(patient.getTelephoneNumber()).isEqualTo("555666777");
        assertThat(patient.getAddress()).isEqualToComparingFieldByField(addressDao.findOne(4L));
        assertThat(patient.getVisits()).size().isEqualTo(2);
    }
}
