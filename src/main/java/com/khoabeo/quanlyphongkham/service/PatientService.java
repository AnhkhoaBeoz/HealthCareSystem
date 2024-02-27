package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.entity.Patient;

import java.util.List;

public interface PatientService {
    public List<PatientDTO> getAllPatient();

    public PatientDTO getPatientByAccountId(long accountId);

    public PatientDTO getPatientById(long id);

    public String addPatient(PatientDTO patientDTO);

    public String updatePatient(long id, PatientDTO patientDTO);

    public String deletePatient(long id);
}
