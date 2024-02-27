package com.khoabeo.quanlyphongkham.mapper;

import com.khoabeo.quanlyphongkham.dto.*;
import com.khoabeo.quanlyphongkham.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface SimpleMapper {
    SimpleMapper MAPPER = Mappers.getMapper(SimpleMapper.class);


    Account accountRequestDTOToAccount(AccountRequestDTO accountDTO);

    @Mapping(source = "roles", target = "roleName", qualifiedByName = "getRoleName")
    AccountResponseDTO accountToAccountResponseDto(Account account);

    @Named("getRoleName")
    default String getRoleName(Set<Role> role) {
        if (!role.isEmpty()) {
            return role.stream().findFirst().get().getName();
        }
        return null;
    }

    RoleDTO roletoRoleDto(Role role);

    Role roleDtoToRole(RoleDTO roleDTO);

    @Mapping(source = "account", target = "accountResponseDTO", qualifiedByName = "responseDoctorAccount")
    DoctorDTO doctorTODoctorDto(Doctor doctor);

    @Named("responseDoctorAccount")
    default AccountResponseDTO responseDoctorAccount(Account account) {
        return SimpleMapper.MAPPER.accountToAccountResponseDto(account);
    }

    Doctor doctorDTOToDoctor(DoctorDTO doctorDTO);

    @Mapping(source = "account", target = "accountResponseDTO", qualifiedByName = "responseNurseAccount")
    NurseDTO nurseTONurseDto(Nurse nurse);

    @Named("responseNurseAccount")
    default AccountResponseDTO responseNurseAccount(Account account) {
        return SimpleMapper.MAPPER.accountToAccountResponseDto(account);
    }

    Nurse nurseDTOToNurse(NurseDTO nurseDTO);

    @Mapping(source = "account", target = "accountResponseDTO", qualifiedByName = "responsePatientAccount")
    PatientDTO patientTOPatientDto(Patient patient);


    @Named("responsePatientAccount")
    default AccountResponseDTO responsePatientAccount(Account account) {
        return SimpleMapper.MAPPER.accountToAccountResponseDto(account);
    }

    Patient patientDTOToPatient(PatientDTO patientDTO);
}

