package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.NurseDTO;
import com.khoabeo.quanlyphongkham.service.NurseService;
import com.khoabeo.quanlyphongkham.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/nurses")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @GetMapping("/")
    public ResponseEntity<List<NurseDTO>> getAllNurse() {

        return ResponseEntity.ok(this.nurseService.getAllNurse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NurseDTO> getNurseById(@PathVariable long id) {
        return ResponseEntity.ok(this.nurseService.getNurseByID(id));
    }

    @PostMapping("/addNurse")
    public ResponseEntity<String> addNurse(@RequestBody NurseDTO NurseDTO) {
        this.nurseService.addNurse(NurseDTO);
        return ResponseEntity.ok("SUCCESSFULLY");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateNurse(@PathVariable long id, @RequestBody NurseDTO NurseDTO) {
        this.nurseService.updateNurse(id, NurseDTO);
        return ResponseEntity.ok("SUCCESSFULLY");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable long id) {
        this.nurseService.deleteNurse(id);
        return ResponseEntity.ok("SUCCESSFULLY");
    }
}
