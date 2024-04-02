package org.example.cpas.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDto {

    private int id;

    @NotEmpty
    private String name;

    private String description;

    private String code;

    private String role_type;
}
