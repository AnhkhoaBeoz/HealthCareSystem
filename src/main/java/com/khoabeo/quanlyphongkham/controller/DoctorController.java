package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<List<DoctorDTO>> getAllDoctor() {

        return ResponseEntity.ok(this.doctorService.getAllDoctor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable long id) {
        return ResponseEntity.ok(this.doctorService.getDoctorByID(id));
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        String result = this.doctorService.addDoctor(doctorDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable long id, @RequestBody DoctorDTO doctorDTO) {
        String result = this.doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long id) {
        String result = this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok(result);
    }

}
