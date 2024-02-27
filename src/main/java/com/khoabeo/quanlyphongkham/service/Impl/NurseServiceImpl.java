package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.NurseDTO;
import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.entity.Nurse;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.NurseRepository;
import com.khoabeo.quanlyphongkham.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<NurseDTO> getAllNurse() {
        return this.nurseRepository.findAll()
                .stream()
                .map(it ->
                        SimpleMapper.MAPPER.nurseTONurseDto(it))
                .collect(Collectors.toList());
    }

    @Override
    public NurseDTO getNurseByID(long id) {
        return SimpleMapper.MAPPER.nurseTONurseDto(
                this.nurseRepository.findById(id).orElseThrow(
                        () -> new ExceptionNotFound("NOT FOUND NURSE WITH ID " + id)
                )
        );
    }

    @Override
    public String addNurse(NurseDTO nurseDTO) {
        Nurse newNurse = SimpleMapper.MAPPER.nurseDTOToNurse(nurseDTO);
        this.nurseRepository.save(newNurse);
        return "Nurse add successfully.";
    }

    @Override
    public String updateNurse(long id, NurseDTO nurseDTO) {
        Nurse getNurse = this.nurseRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("NOT FOUND NURSE WITH ID " + id));

        String name = nurseDTO.getName();

        if (!name.isEmpty()) {
            getNurse.setName(name);
            // Find the account
            String userName = nurseDTO.getAccountResponseDTO().getUserName();
            Optional<Account> account = this.accountRepository.findAccountByUserName(userName);
            if (account.isPresent() && !account.get().isAssigned()) {
                account.get().setAssigned(true);
                getNurse.setAccount(account.get());
                this.accountRepository.save(account.get());
            } else if (account.isPresent() && account.get().isAssigned()) {
                throw new ExceptionNotFound("Account was assigned!!");
            }

            this.nurseRepository.save(getNurse);
            return "Nurse updated successfully.";
        }

        return "Name is empty.";
    }


    @Override
    public void deleteNurse(long id) {
        this.nurseRepository.deleteById(id);
        return;
    }
}
