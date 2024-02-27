package com.khoabeo.quanlyphongkham.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DutyScheduleRequest {
    private Long doctorId;
    private Long nurseId;
    private LocalDateTime scheduleDate;
    private String shift;
}
