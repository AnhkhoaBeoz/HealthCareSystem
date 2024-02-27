package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
