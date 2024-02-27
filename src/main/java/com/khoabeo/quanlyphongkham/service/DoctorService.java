package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.entity.Doctor;

import java.util.List;

public interface DoctorService {
    public List<DoctorDTO> getAllDoctor();

    public DoctorDTO getDoctorByID(long id);

    public String addDoctor(DoctorDTO doctorDTO);

    public String updateDoctor(long id, DoctorDTO doctorDTO);

    public String deleteDoctor(long id);
}
