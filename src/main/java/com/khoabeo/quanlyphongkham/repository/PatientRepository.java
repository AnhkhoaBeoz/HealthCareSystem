package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByAccountId(Long accountId);

    Optional<Patient> findPatientById(long id);
}
