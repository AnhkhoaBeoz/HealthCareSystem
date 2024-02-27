package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.Constants.Status;
import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.entity.Appointment;
import com.khoabeo.quanlyphongkham.entity.Doctor;
import com.khoabeo.quanlyphongkham.entity.Patient;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.AppointmentRepository;
import com.khoabeo.quanlyphongkham.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return this.appointmentRepository.findAll();
    }

    public String registerAppointment(PatientDTO patientDTO, DoctorDTO doctorDTO) {
        Patient patient = SimpleMapper.MAPPER.patientDTOToPatient(patientDTO);
        Doctor doctor = SimpleMapper.MAPPER.doctorDTOToDoctor(doctorDTO);
        Appointment appointment = new Appointment(Status.PENDING, doctor, patient);
        return "Register Appointment Success!!!";
    }
}
