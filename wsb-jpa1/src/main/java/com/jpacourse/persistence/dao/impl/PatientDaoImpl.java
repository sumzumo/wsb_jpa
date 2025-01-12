package com.jpacourse.persistence.dao.impl;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    @Transactional
    public PatientEntity findPatientById(Long patientId) {
        return entityManager.find(PatientEntity.class, patientId);
    }
    @Override
    @Transactional
    public Collection<PatientEntity> findPatientsByLastName(String lastName) {
        String query = "SELECT pe FROM PatientEntity pe WHERE :lastName IS NULL OR pe.lastName = :lastName";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }
    @Override
    @Transactional
    public Collection<PatientEntity> findPatientsWithVisitsCountGreaterThan(int visitsCount) {
        if (visitsCount < 0) {
            throw new IllegalArgumentException("Visits count cannot be negative.");
        }
        String query = "SELECT pe FROM PatientEntity pe WHERE size(pe.visits) > :visitsCount";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("visitsCount", visitsCount)
                .getResultList();
    }

    @Override
    @Transactional
    public Collection<PatientEntity> findPatientsByFirstTimeStatus(Boolean isFirstTime) {
        String query = "SELECT pe FROM PatientEntity pe WHERE :firstTimeStatus IS NULL OR pe.isFirstTime = :firstTimeStatus";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("firstTimeStatus", isFirstTime)
                .getResultList();
    }

    @Override
    @Transactional
    public PatientEntity saveOrUpdate(PatientEntity patientEntity) {
        return entityManager.merge(patientEntity);
    }

    @Override
    @Transactional
    public void deletePatientById(Long patientId) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient != null) {
            entityManager.remove(patient);
        }
    }



    @Override
    @Transactional
    public void addVisitToPatient(Long patientId, Long doctorId, String description, LocalDateTime time) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient != null) {
            VisitEntity visit = new VisitEntity();
            visit.setDescription(description);
            visit.setTime(time);
            visit.setDoctor(entityManager.find(DoctorEntity.class, doctorId));
            visit.setPatient(patient);
            patient.getVisits().add(visit);
            entityManager.merge(patient);
        }
    }
}