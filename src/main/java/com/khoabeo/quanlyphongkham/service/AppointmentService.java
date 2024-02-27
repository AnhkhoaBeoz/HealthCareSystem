package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAllAppointment();
    public String registerAppointment(PatientDTO patientDTO, DoctorDTO doctorDTO);
}
