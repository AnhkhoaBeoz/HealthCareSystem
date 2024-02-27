package com.khoabeo.quanlyphongkham.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String name;
    private String address;
    private String phone;
    private LocalDateTime birthday;
    private String gender;
    private AccountResponseDTO accountResponseDTO;
}
