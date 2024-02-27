package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.dto.RequestAppointment;
import com.khoabeo.quanlyphongkham.entity.Appointment;
import com.khoabeo.quanlyphongkham.service.AppointmentService;
import com.khoabeo.quanlyphongkham.service.DoctorService;
import com.khoabeo.quanlyphongkham.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/appointments")
public class AppointmentController {

    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {

        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        return ResponseEntity.ok(this.appointmentService.getAllAppointment());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAppointment(@RequestBody RequestAppointment requestAppointment) {
        PatientDTO patientCurrent = this.patientService.getPatientByAccountId(requestAppointment.getAccountId());

        DoctorDTO doctorRegister = this.doctorService.getDoctorByID(requestAppointment.getDoctorId());

        return ResponseEntity.ok(this.appointmentService.registerAppointment(patientCurrent, doctorRegister));
    }
}
