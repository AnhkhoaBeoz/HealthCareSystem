package com.khoabeo.quanlyphongkham.dto;

import com.khoabeo.quanlyphongkham.entity.DutySchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DutyScheduleResponse {
    private List<DutySchedule> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
