package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.entity.Doctor;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.DoctorRepository;
import com.khoabeo.quanlyphongkham.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;
    private AccountRepository accountRepository;

    @Autowired

    public DoctorServiceImpl(DoctorRepository doctorRepository, AccountRepository accountRepository) {
        this.doctorRepository = doctorRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<DoctorDTO> getAllDoctor() {
        return this.doctorRepository.findAll().stream().map(
                it -> SimpleMapper.MAPPER.doctorTODoctorDto(it)
        ).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorByID(long id) {

        return SimpleMapper.MAPPER.doctorTODoctorDto(this.doctorRepository.findById(id).orElseThrow(
                () -> new ExceptionNotFound("NOT FOUND DOCTOR WITH ID: " + id)
        ));
    }

    @Override
    public String addDoctor(DoctorDTO doctorDTO) {
        Doctor newDoctor = SimpleMapper.MAPPER.doctorDTOToDoctor(doctorDTO);
        this.doctorRepository.save(newDoctor);
        return "Doctor added successfully.";
    }

    @Override
    public String updateDoctor(long id, DoctorDTO doctorDTO) {
        Doctor getDoctorExist = this.doctorRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("NOT FOUND DOCTOR WITH ID " + id));

        String name = doctorDTO.getName();
        String specialization = doctorDTO.getSpecialization();

        if (!name.isEmpty() && !specialization.isEmpty()) {
            getDoctorExist.setName(name);
            getDoctorExist.setSpecialization(specialization);

            // Tìm tài khoản
            String userName = doctorDTO.getAccountResponseDTO().getUserName();
            Optional<Account> account = this.accountRepository.findAccountByUserName(userName);
            if (account.isPresent() && !account.get().isAssigned()) {
                account.get().setAssigned(true);
                getDoctorExist.setAccount(account.get());
                this.accountRepository.save(account.get());
            } else if (account.get().isAssigned()) {
                throw new ExceptionNotFound("Account was assign!!");
            }

            this.doctorRepository.save(getDoctorExist);
            return "Doctor updated successfully.";
        }

        return "Name or specialization is empty.";
    }


    @Override
    public String deleteDoctor(long id) {
        Doctor doctor = this.doctorRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("CANNOT DELETE BECAUSE DOCTOR WITH ID" + id + " DOES NOT EXIST"));

        this.doctorRepository.delete(doctor);
        return "Doctor deleted successfully.";
    }

}
