package com.jpacourse.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
@Service
@Transactional

public class VisitServiceImpl implements VisitService
{
    private final VisitDao visitDao;
    @Autowired
    public VisitServiceImpl(VisitDao pVisitDao){
        this.visitDao = pVisitDao;
    }
    @Override
    public VisitTO findById(Long id)
    {
        final VisitEntity entity = visitDao.findOne(id);
        return VisitMapper.mapToTO(entity);
    }
    @Override
    public Collection<VisitTO> findAllByPatientId(Long patientId) {
        final Collection<VisitEntity> entities = visitDao.findByPatient(patientId);
        return entities
                .stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
    private VisitTO mapToVisitTO(VisitEntity visitEntity) {
        return VisitMapper.mapToTO(visitEntity);
    }
}