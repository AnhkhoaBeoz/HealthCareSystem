package com.khoabeo.quanlyphongkham.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private long id;
    private String userName;
    private String email;
    private String avatar;
    private String roleName;
}