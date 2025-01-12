package com.jpacourse.persistence.dao.impl;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;
import java.util.Collection;
@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao
{
    @Override
    public Collection<VisitEntity> findByPatient(Long patientId) {
        if (patientId == null) {
            throw new IllegalArgumentException("Patient ID cannot be null.");
        }

        String query = "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId";
        return entityManager.createQuery(query, VisitEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

}