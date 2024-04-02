package org.example.cpas.services.impl;
import org.example.cpas.dto.RoleDto;
import org.example.cpas.entities.Role;
import org.example.cpas.repository.RoleRepository;
import org.example.cpas.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto createRole(RoleDto roleDto){
        Role role = new Role();
        role.setName(roleDto.getName());
        Role savedRole = this.roleRepository.save(role);

        return this.modelMapper.map(savedRole, RoleDto.class);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Integer roleId) {
        Role role = this.roleRepository.findById(roleId).orElseThrow();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setCode(roleDto.getCode());
        role.setRole_type(roleDto.getRole_type());

        Role updatedRole = this.roleRepository.save(role);
        return this.modelMapper.map(updatedRole, RoleDto.class);
    }

    @Override
    public RoleDto getRole(Integer roleId) {
        Role role = this.roleRepository.findById(roleId).orElseThrow();
        return this.modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDto> rolesDto = roles.stream().map(role -> this.modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return rolesDto;
    }

    @Override
    public void deleteRole(Integer roleID) {
        Role role = this.roleRepository.findById(roleID).orElseThrow();
        this.roleRepository.delete(role);
    }
}
