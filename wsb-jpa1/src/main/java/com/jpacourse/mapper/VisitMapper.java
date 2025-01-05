package com.jpacourse.mapper;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;
import java.util.stream.Collectors;
public class VisitMapper
{
    public static VisitTO mapPatientToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctor()));
        visitTO.setTime(visitEntity.getTime());
        visitTO.setTreatments(visitEntity.getMedicalTreatments() != null ? visitEntity.getMedicalTreatments().stream()
                .map(MedicalTreatmentMapper::mapToTO).collect(Collectors.toList()) : null
        );
        return visitTO;
    }
    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctor()));
        visitTO.setTime(visitEntity.getTime());
        visitTO.setTreatments(visitEntity.getMedicalTreatments() != null ? visitEntity.getMedicalTreatments().stream()
                .map(MedicalTreatmentMapper::mapToTO).collect(Collectors.toList()) : null
        );
        visitTO.setPatientEntity(PatientMapper.mapToTO(visitEntity.getPatient()));
        return visitTO;
    }
    public static VisitEntity mapForPatientToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        final VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setMedicalTreatments(visitTO.getTreatments() != null ? visitTO.getTreatments().stream()
                .map(MedicalTreatmentMapper::mapToEntity).collect(Collectors.toList()) : null
        );
        return visitEntity;
    }
    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        final VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setMedicalTreatments(visitTO.getTreatments() != null ? visitTO.getTreatments().stream()
                .map(MedicalTreatmentMapper::mapToEntity).collect(Collectors.toList()) : null
        );
        visitEntity.setPatient(PatientMapper.mapToEntity(visitTO.getPatientEntity()));
        return visitEntity;
    }
}