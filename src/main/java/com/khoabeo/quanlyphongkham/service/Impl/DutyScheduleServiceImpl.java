package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.DutyScheduleRequest;
import com.khoabeo.quanlyphongkham.dto.DutyScheduleResponse;
import com.khoabeo.quanlyphongkham.entity.Doctor;
import com.khoabeo.quanlyphongkham.entity.DutySchedule;
import com.khoabeo.quanlyphongkham.entity.Nurse;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.repository.DoctorRepository;
import com.khoabeo.quanlyphongkham.repository.DutyScheduleRepository;
import com.khoabeo.quanlyphongkham.repository.NurseRepository;
import com.khoabeo.quanlyphongkham.repository.PatientRepository;
import com.khoabeo.quanlyphongkham.service.DutyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DutyScheduleServiceImpl implements DutyScheduleService {

    private DutyScheduleRepository dutyScheduleRepository;

    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;
    private PatientRepository patientRepository;

    @Autowired
    public DutyScheduleServiceImpl(DutyScheduleRepository dutyScheduleRepository, DoctorRepository doctorRepository, NurseRepository nurseRepository, PatientRepository patientRepository) {

        this.dutyScheduleRepository = dutyScheduleRepository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public String createDutySchedule(Doctor doctor, Nurse nurse, LocalDateTime scheduleDate, String shift) {
        List<DutySchedule> dutyScheduleList = this.dutyScheduleRepository.findAll();

        for (DutySchedule dutySchedule : dutyScheduleList) {
            boolean checkDoctor = doctor.equals(dutySchedule.getDoctor());
            boolean checkNurse = nurse.equals(dutySchedule.getNurse());

            // Kiểm tra trùng bác sĩ hoặc y tá và ngày, ca trực
            if ((checkDoctor || checkNurse) &&
                    scheduleDate.compareTo(dutySchedule.getScheduleDate()) == 0 &&
                    shift.equals(dutySchedule.getShift())) {

                throw new RuntimeException("EXIST SCHEDULE OF THIS DOCTOR OR THIS NURSE !!! ");
            }
        }

        DutySchedule dutySchedule = new DutySchedule();
        dutySchedule.setDoctor(doctor);
        dutySchedule.setNurse(nurse);
        dutySchedule.setScheduleDate(scheduleDate);
        dutySchedule.setShift(shift);
        dutySchedule.setStatus(DutySchedule.DutyScheduleStatus.PENDING);
        dutyScheduleRepository.save(dutySchedule);
        return "Create DutySchedule Success !!!";
    }


    @Override
    public DutyScheduleResponse getAllDutySchedules(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<DutySchedule> page = this.dutyScheduleRepository.findAll(pageable);

        List<DutySchedule> content = page.getContent();

        DutyScheduleResponse dutyScheduleResponse = new DutyScheduleResponse();
        dutyScheduleResponse.setContent(content);
        dutyScheduleResponse.setPageNo(page.getNumber());
        dutyScheduleResponse.setPageSize(page.getSize());
        dutyScheduleResponse.setTotalElements(page.getTotalElements());
        dutyScheduleResponse.setTotalPages(page.getTotalPages());
        dutyScheduleResponse.setLast(page.isLast());

        return dutyScheduleResponse;
    }


    @Override
    public Optional<DutySchedule> getDutyScheduleById(Long id) {
        return dutyScheduleRepository.findById(id);
    }

    @Override
    public String updateDutySchedule(Long id, DutyScheduleRequest dutyScheduleRequest) {
        Optional<DutySchedule> optionalDutySchedule = dutyScheduleRepository.findById(id);
        optionalDutySchedule.ifPresent(dutySchedule -> {
            if (dutyScheduleRequest.getDoctorId() != null) {
                dutySchedule.setDoctor(this.doctorRepository.findById(dutyScheduleRequest.getDoctorId()).orElseThrow(
                        () -> new ExceptionNotFound("NOT EXIST DOCTOR WITH " + dutyScheduleRequest.getDoctorId())
                ));
            }
            if (dutyScheduleRequest.getNurseId() != null) {
                dutySchedule.setNurse(this.nurseRepository.findById(dutyScheduleRequest.getNurseId()).orElseThrow(
                        () -> new ExceptionNotFound("NOT EXIST NURSE WITH " + dutyScheduleRequest.getNurseId())
                ));
            }
            if (dutyScheduleRequest.getScheduleDate() != null) {
                dutySchedule.setScheduleDate(dutyScheduleRequest.getScheduleDate());
            }
            if (dutyScheduleRequest.getShift() != null) {
                dutySchedule.setShift(dutyScheduleRequest.getShift());
            }
            dutyScheduleRepository.save(dutySchedule);
        });
        return "UPDATE DUTY SCHEDULE SUCCESS!!!!";
    }


    @Override
    public void deleteDutySchedule(Long id) {
        dutyScheduleRepository.deleteById(id);
    }
}
