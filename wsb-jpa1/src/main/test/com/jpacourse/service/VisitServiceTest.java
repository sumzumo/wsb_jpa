package com.jpacourse.service;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testShouldFindAllVisitsByPatientId() {
        // given
        Long patientId = 1L;
        // when
        Collection<VisitTO> visits = visitService.findAllByPatientId(patientId);
        List<VisitTO> listOfVisits = new ArrayList<>(visits);
        // then
        assertNotNull(listOfVisits);
        assertThat(listOfVisits).hasSize(1);


        VisitTO firstVisit = listOfVisits.get(0);
        assertThat(firstVisit).isNotNull();
        assertThat(firstVisit.getId()).isEqualTo(1L);
        assertThat(firstVisit.getDescription()).isEqualTo("Routine check-up");
        assertThat(firstVisit.getTime()).isEqualTo(LocalDateTime.parse("2024-12-06T10:00:00"));


        PatientTO patient = firstVisit.getPatientEntity();
        assertThat(patient).isNotNull();
        assertThat(patient.getId()).isEqualTo(1L);
        assertThat(patient.getFirstName()).isEqualTo("Ania");
        assertThat(patient.getLastName()).isEqualTo("Blabla");


        DoctorTO doctor = firstVisit.getDoctor();
        assertThat(doctor).isNotNull();
        assertThat(doctor.getFirstName()).isEqualTo("Blaze");
        assertThat(doctor.getLastName()).isEqualTo("Bronka");
    }
}
