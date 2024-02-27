package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.AccountRequestDTO;
import com.khoabeo.quanlyphongkham.dto.AccountResponseDTO;
import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.entity.Role;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.RoleRepository;
import com.khoabeo.quanlyphongkham.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AccountResponseDTO> findAllAccounts() {

        List<AccountResponseDTO> accountDTOList = this.accountRepository.findAll().stream().map(
                it -> SimpleMapper.MAPPER.accountToAccountResponseDto(it)
        ).collect(Collectors.toList());

        return accountDTOList;
    }

    @Override
    public AccountResponseDTO findById(long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("NOT FOUND ACCOUNT WITH " + id));
        AccountResponseDTO accountDTO = SimpleMapper.MAPPER.accountToAccountResponseDto(account);
        String roleName = null;
        Optional<Role> roleOptional = account.getRoles().stream().findFirst();
        if (roleOptional.isPresent()) {
            roleName = roleOptional.get().getName();
        }
        accountDTO.setRoleName(roleName);
        return accountDTO;
    }

    @Override
    public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO) {
        // Tìm kiếm vai trò trong cơ sở dữ liệu
        Role getRole = this.roleRepository.findRoleByName(accountRequestDTO.getRoleName());
        String passEnd = this.passwordEncoder.encode(accountRequestDTO.getPassword());
        // Tạo một tài khoản mới và thiết lập vai trò
        Account newAccount = SimpleMapper.MAPPER.accountRequestDTOToAccount(accountRequestDTO);
        newAccount.setPassword(passEnd);
        newAccount.setRoles(Collections.singleton(getRole)); // Sử dụng Collections.singleton để tạo Set<Role> chỉ chứa một phần tử

        // Lưu tài khoản mới vào cơ sở dữ liệu và chuyển đổi thành DTO trước khi trả về
        return SimpleMapper.MAPPER.accountToAccountResponseDto(this.accountRepository.save(newAccount));
    }


    @Override
    public AccountResponseDTO updateRoleUser(long id, String role) {
        Role getRole = this.roleRepository.findByName(role).orElseThrow(() ->
                new ExceptionNotFound("NOT FOUND ROLE")
        );
        Account getAccountExist = this.accountRepository
                .findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("CANT NOT UPDATE BECAUSE NOT FOUND ACCOUNT WITH " + id)
                );
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(getRole);
        getAccountExist.setRoles(newRoles);
        Account updatedAccount = accountRepository.save(getAccountExist);

        return SimpleMapper.MAPPER.accountToAccountResponseDto(updatedAccount);
    }

    @Override
    public AccountResponseDTO updateFieldAccount(long id, AccountRequestDTO accountRequestDTO) {
        Account getAccount = this.accountRepository.findById(id).orElseThrow(
                () -> new ExceptionNotFound("CANT UPDATE BECAUSE NOT FOUND ACCOUNT ")
        );
        Account accountUpdate = SimpleMapper.MAPPER.accountRequestDTOToAccount(accountRequestDTO);
        getAccount.setAvatar(accountUpdate.getAvatar());
        getAccount.setPassword(accountUpdate.getPassword());
        getAccount.setEmail(accountUpdate.getEmail());
        return SimpleMapper.MAPPER.accountToAccountResponseDto(this.accountRepository.save(getAccount));

    }

    @Override
    public void deleteAccount(long id) {
        this.accountRepository.deleteById(id);
        return;
    }
}
