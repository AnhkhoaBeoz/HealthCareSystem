package com.khoabeo.quanlyphongkham.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NurseDTO {
    private long id;
    private String name;
    private AccountResponseDTO accountResponseDTO;

}
