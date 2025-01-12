package com.jpacourse.persistence.dao;
import com.jpacourse.persistence.entity.VisitEntity;
import java.util.Collection;
public interface VisitDao extends Dao<VisitEntity, Long>
{
    Collection<VisitEntity> findByPatient(Long patientId);
}