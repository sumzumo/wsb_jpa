package com.jpacourse.persistence.dao;
import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long> {
    PatientEntity findPatientById(Long patientId);
    PatientEntity saveOrUpdate(PatientEntity patientEntity);
    void deletePatientById(Long patientId);
    void addVisitToPatient(Long patientId, Long doctorId, String description, LocalDateTime time);
}