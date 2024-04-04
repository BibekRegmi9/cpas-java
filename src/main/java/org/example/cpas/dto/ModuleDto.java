package org.example.cpas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class ModuleDto {

    private int id;
    private String name;
    private String description;
    private String code;
    private boolean is_active;

    private List<PrivilegeDto> privilege;

    private int[] screen_id;


    @Data
    public static class PrivilegeDto{
        private Integer privilege_id;
        private String method;
        private String endpoint;

    }

}
