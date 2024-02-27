package com.khoabeo.quanlyphongkham.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
    private long id;
    @NotBlank(message = "userName is mandatory")
    private String userName;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "avatar is mandatory")
    private String avatar;
    @NotBlank(message = "avatar is mandatory")
    private String roleName;
}
