package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
