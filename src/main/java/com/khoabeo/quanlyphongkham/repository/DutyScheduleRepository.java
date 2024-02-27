package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.DutySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyScheduleRepository extends JpaRepository<DutySchedule,Long> {

}
