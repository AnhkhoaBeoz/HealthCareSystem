package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.Constants.AppConstants;
import com.khoabeo.quanlyphongkham.dto.DutyScheduleRequest;
import com.khoabeo.quanlyphongkham.dto.DutyScheduleResponse;
import com.khoabeo.quanlyphongkham.entity.Doctor;
import com.khoabeo.quanlyphongkham.entity.Nurse;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.service.DoctorService;
import com.khoabeo.quanlyphongkham.service.DutyScheduleService;
import com.khoabeo.quanlyphongkham.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/api/dutyschedules")
public class DutyScheduleController {

    private DutyScheduleService dutyScheduleService;
    private DoctorService doctorService;
    private NurseService nurseService;

    public DutyScheduleController(DutyScheduleService dutyScheduleService, DoctorService doctorService, NurseService nurseService) {
        this.dutyScheduleService = dutyScheduleService;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
    }

    @GetMapping("/")
    public DutyScheduleResponse getAllDutySchedule(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {

        return this.dutyScheduleService.getAllDutySchedules(pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/createSchedule")
    public ResponseEntity<String> createDutySchedule(@RequestBody DutyScheduleRequest request) {
        Doctor doctor = SimpleMapper.MAPPER.doctorDTOToDoctor(this.doctorService.getDoctorByID(request.getDoctorId()));
        Nurse nurse = SimpleMapper.MAPPER.nurseDTOToNurse(this.nurseService.getNurseByID(request.getNurseId()));
        return ResponseEntity.ok(this.dutyScheduleService.createDutySchedule(doctor, nurse, request.getScheduleDate(), request.getShift()));
    }

    @PutMapping("/updateSchedule/{id}")
    public ResponseEntity<String> updateDutySchedule(@PathVariable Long id, @RequestBody DutyScheduleRequest dutyScheduleRequest) {

        return ResponseEntity.ok(this.dutyScheduleService.updateDutySchedule(id, dutyScheduleRequest));
    }

}
