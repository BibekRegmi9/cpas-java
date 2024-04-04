package org.example.cpas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModulePrivilegeMappingDto {

    private int id;
    private String name;
    private String description;
    private String code;
    private boolean is_active;

}
