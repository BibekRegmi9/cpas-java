package org.example.cpas.services;

import org.example.cpas.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto role);

    RoleDto updateRole(RoleDto role, Integer roleId);

    RoleDto getRole(Integer roleId);

    List<RoleDto> getAllRoles();

    void deleteRole(Integer roleID);
}
