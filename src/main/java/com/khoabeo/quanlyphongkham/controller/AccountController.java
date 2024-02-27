package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.AccountRequestDTO;
import com.khoabeo.quanlyphongkham.dto.AccountResponseDTO;
import com.khoabeo.quanlyphongkham.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<AccountResponseDTO>> getListAccount() {
        return ResponseEntity.ok(this.accountService.findAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getListAccount(@PathVariable long id) {

        return ResponseEntity.ok((this.accountService.findById(id)));
    }

        @PostMapping("/addAccount")
    public ResponseEntity<AccountResponseDTO> addAccount(@Validated
                                                 @RequestBody AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok(
                this.accountService.addAccount(accountRequestDTO)
        );
    }

    @PatchMapping("/update/{id}/role/{role}")
    public ResponseEntity<AccountResponseDTO> updateRoleAccount(@PathVariable long id, @PathVariable String role) {
        return ResponseEntity.ok(this.accountService.updateRoleUser(id, role));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponseDTO> updateFieldAccount(@PathVariable long id,
                                                                 @RequestBody AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok(this.accountService.updateFieldAccount(id, accountRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        this.accountService.deleteAccount(id);
        return ResponseEntity.ok("DELETE SUCCESSFULLY WITH id : " + id);
    }

}
