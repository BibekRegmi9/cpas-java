package org.example.cpas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String citizenNo;
    private String gender;
    private boolean is_active;
    private List<RoleDto> roleDtos;
}
