package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.AccountRequestDTO;
import com.khoabeo.quanlyphongkham.dto.AccountResponseDTO;


import java.util.List;

public interface AccountService {

    AccountResponseDTO findById(long id);

    AccountResponseDTO updateRoleUser(long id, String role);

    AccountResponseDTO updateFieldAccount(long id, AccountRequestDTO accountDTO);

    List<AccountResponseDTO> findAllAccounts();

    AccountResponseDTO addAccount(AccountRequestDTO accountDTO);

    void deleteAccount(long id);
}
