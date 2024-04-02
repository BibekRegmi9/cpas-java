package org.example.cpas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRoleMappingDto {

    private boolean is_active;
    private int user_id;
    private int role_id;
}
