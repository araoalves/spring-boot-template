package com.template.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String telefone;
    private String email;
    private Date data_cadastro;
}
