package org.example.cpas.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModuleDto {

    private int id;
    private String name;
    private String description;
    private String code;
    private boolean is_active;

}
