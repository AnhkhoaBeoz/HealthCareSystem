package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.DutyScheduleRequest;
import com.khoabeo.quanlyphongkham.dto.DutyScheduleResponse;
import com.khoabeo.quanlyphongkham.entity.Doctor;
import com.khoabeo.quanlyphongkham.entity.DutySchedule;
import com.khoabeo.quanlyphongkham.entity.Nurse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DutyScheduleService {
    String createDutySchedule(Doctor doctor, Nurse nurse, LocalDateTime scheduleDate, String shift);

    DutyScheduleResponse getAllDutySchedules(int pageNo, int pageSize, String sortBy, String sortDir);

    Optional<DutySchedule> getDutyScheduleById(Long id);

    public String updateDutySchedule(Long id, DutyScheduleRequest dutyScheduleRequest);

    void deleteDutySchedule(Long id);
}
