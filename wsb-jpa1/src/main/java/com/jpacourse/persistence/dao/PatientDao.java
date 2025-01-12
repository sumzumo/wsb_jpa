package com.jpacourse.persistence.dao;
import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.Collection;

public interface PatientDao extends Dao<PatientEntity, Long> {
    PatientEntity findPatientById(Long patientId);
    PatientEntity saveOrUpdate(PatientEntity patientEntity);
    Collection<PatientEntity> findPatientsByLastName(String lastName);
    Collection<PatientEntity> findPatientsWithVisitsCountGreaterThan(int visitsCount);
    Collection<PatientEntity> findPatientsByFirstTimeStatus(Boolean isFirstTime);
    void deletePatientById(Long patientId);
    void addVisitToPatient(Long patientId, Long doctorId, String description, LocalDateTime time);
}