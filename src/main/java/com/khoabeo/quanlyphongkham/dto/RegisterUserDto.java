package com.khoabeo.quanlyphongkham.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String username;
    private String password;
    private String email;
    private String avatar;
}
