package com.jpacourse.mapper;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;
import java.util.stream.Collectors;
public final class PatientMapper
{
    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setIsFirstTime(patientEntity.getIsFirstTime());
        if (patientEntity.getAddress() != null) {
            patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        }
        if (patientEntity.getVisits() != null) {
            patientTO.setVisits(patientEntity.getVisits() != null ? patientEntity.getVisits().stream()
                    .map(VisitMapper::mapPatientToTO).collect(Collectors.toList()) : null
            );
        }
        return patientTO;
    }
    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if (patientTO == null) {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setIsFirstTime(patientTO.getIsFirstTime());
        if (patientTO.getAddress() != null) {
            patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        }
        if (patientTO.getVisits() != null) {
            patientEntity.setVisits(patientTO.getVisits() != null ? patientTO.getVisits().stream()
                    .map(VisitMapper::mapForPatientToEntity).collect(Collectors.toList()) : null
            );
        }
        return patientEntity;
    }
}