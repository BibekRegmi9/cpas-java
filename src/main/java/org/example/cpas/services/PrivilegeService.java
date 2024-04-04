package org.example.cpas.services;


import org.example.cpas.dto.PrivilegeDto;

import java.util.List;

public interface PrivilegeService {

    PrivilegeDto createPrivilege(PrivilegeDto privilege);

    PrivilegeDto updatePrivilege(PrivilegeDto privilege, Integer privilegeId);

    PrivilegeDto getPrivilege(Integer privilegeId);

    List<PrivilegeDto> getAllPrivilege();

    void deletePrivilege(Integer privilegeId);

}
