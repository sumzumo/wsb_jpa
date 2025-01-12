package com.jpacourse.mapper;
import com.jpacourse.dto.DoctorTO;
import com.jpacourse.persistence.entity.DoctorEntity;

public final class DoctorMapper
{
    private DoctorMapper() {
    }

    public static DoctorTO mapToTO(final DoctorEntity doctorEntity)
    {
        if (doctorEntity == null) {
            return null;
        }
        final DoctorTO doctorTO = new DoctorTO();
        doctorTO.setDoctorNumber(doctorEntity.getDoctorNumber());
        doctorTO.setAddress(AddressMapper.mapToTO(doctorEntity.getAddress()));
        doctorTO.setEmail(doctorEntity.getEmail());
        doctorTO.setId(doctorEntity.getId());
        doctorTO.setTelephoneNumber(doctorEntity.getTelephoneNumber());
        doctorTO.setFirstName(doctorEntity.getFirstName());
        doctorTO.setLastName(doctorEntity.getLastName());
        doctorTO.setSpecialization(doctorEntity.getSpecialization());
        return doctorTO;
    }
    public static DoctorEntity mapToEntity(final DoctorTO doctorTO)
    {
        if (doctorTO == null) {
            return null;
        }
        final DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setDoctorNumber(doctorTO.getDoctorNumber());
        doctorEntity.setAddress(AddressMapper.mapToEntity(doctorTO.getAddress()));
        doctorEntity.setSpecialization(doctorTO.getSpecialization());
        doctorEntity.setEmail(doctorTO.getEmail());
        doctorEntity.setId(doctorTO.getId());
        doctorEntity.setTelephoneNumber(doctorTO.getTelephoneNumber());
        doctorEntity.setFirstName(doctorTO.getFirstName());
        doctorEntity.setLastName(doctorTO.getLastName());
        return doctorEntity;
    }
}