package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.AccountResponseDTO;
import com.khoabeo.quanlyphongkham.dto.PatientDTO;
import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.entity.Patient;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.PatientRepository;
import com.khoabeo.quanlyphongkham.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, AccountRepository accountRepository) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<PatientDTO> getAllPatient() {
        return this.patientRepository.findAll().stream().map(it -> SimpleMapper.MAPPER.patientTOPatientDto(it)).collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientByAccountId(long accountId) {
        Patient patient = this.patientRepository.findByAccountId(accountId);

        PatientDTO patientDTO = SimpleMapper.MAPPER.patientTOPatientDto(patient);

        return patientDTO;
    }

    @Override
    public PatientDTO getPatientById(long id) {
        return SimpleMapper
                .MAPPER.patientTOPatientDto(this.patientRepository.findPatientById(id)
                        .orElseThrow(()
                                -> new ExceptionNotFound("CAN'T GET PATIENT BECAUSE ID " + id + " NOT EXIST")));
    }

    @Override
    public String addPatient(PatientDTO patientDTO) {
        Patient newPatient = SimpleMapper.MAPPER.patientDTOToPatient(patientDTO);
        if (!patientDTO.getAccountResponseDTO().getUserName().isEmpty()) {
            Account accountAssign = this.accountRepository.findAccountByUserName(patientDTO.getAccountResponseDTO().getUserName()).orElseThrow(
                    () -> new ExceptionNotFound("Cannot assign because the account is already assigned or does not exist"));

            accountAssign.setAssigned(true);
            newPatient.setAccount(accountAssign);
            this.accountRepository.save(accountAssign);
        }
        this.patientRepository.save(newPatient);
        return "Patient add success!!!!";
    }

    @Override
    public String updatePatient(long id, PatientDTO patientDTO) {
        Patient getPatient = this.patientRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("NOT FOUND PATIENT WITH ID " + id));

        String name = patientDTO.getName();
        if (!name.isEmpty()) {
            getPatient.setName(name);
        }

        String address = patientDTO.getAddress();
        if (address != null) {
            getPatient.setAddress(address);
        }

        String phone = patientDTO.getPhone();
        if (phone != null) {
            getPatient.setPhone(phone);
        }

        LocalDateTime birthday = patientDTO.getBirthday();
        if (birthday != null) {
            getPatient.setBirthday(birthday);
        }

        String gender = patientDTO.getGender();
        if (gender != null) {
            getPatient.setGender(gender);
        }

        AccountResponseDTO accountResponseDTO = patientDTO.getAccountResponseDTO();
        if (accountResponseDTO != null) {
            String userName = accountResponseDTO.getUserName();
            Optional<Account> account = this.accountRepository.findAccountByUserName(userName);
            if (account.isPresent() && !account.get().isAssigned()) {
                account.get().setAssigned(true);
                getPatient.setAccount(account.get());
                this.accountRepository.save(account.get());
            } else if (account.isPresent() && account.get().isAssigned()) {
                throw new ExceptionNotFound("Account is already assigned!!");
            } else if (!account.isPresent()) {
                throw new ExceptionNotFound("DOES NOT EXITS ACCOUNT!!!");
            }
        }

        this.patientRepository.save(getPatient);
        return "Patient updated successfully.";
    }
    @Override
    public String deletePatient(long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patientRepository.delete(patient);
            return "Patient with ID " + id + " deleted successfully.";
        } else {
            throw new ExceptionNotFound("Patient with ID " + id + " not found.");
        }
    }


}
