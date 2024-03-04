package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.service.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<PatientDTO>> getAllPatient() {

        return ResponseEntity.ok(this.patientService.getAllPatient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable long id) {
        return ResponseEntity.ok(this.patientService.getPatientById(id));
    }
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity
                .ok(this.patientService.addPatient(patientDTO));
    }
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable long id, @RequestBody PatientDTO patientDTO) {

        return ResponseEntity.ok(this.patientService.updatePatient(id, patientDTO));
    }
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable long id) {
        return ResponseEntity.ok(this.patientService.deletePatient(id));
    }
}
