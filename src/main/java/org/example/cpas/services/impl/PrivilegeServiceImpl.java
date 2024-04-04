package org.example.cpas.services.impl;

import org.example.cpas.dto.PrivilegeDto;
import org.example.cpas.entities.Privilege;
import org.example.cpas.repository.PrivilegeRepository;
import org.example.cpas.services.PrivilegeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrivilegeDto createPrivilege(PrivilegeDto privilegeDto) {
        Privilege privilege = this.modelMapper.map(privilegeDto, Privilege.class);
        Privilege savedPrivilege = this.privilegeRepository.save(privilege);
        return this.modelMapper.map(savedPrivilege, PrivilegeDto.class);
    }

    @Override
    public PrivilegeDto updatePrivilege(PrivilegeDto privilegeDto, Integer privilegeId) {
        Privilege privilege = this.privilegeRepository.findById(privilegeId).orElseThrow();
        privilege.setName(privilegeDto.getName());
        privilege.setDescription(privilegeDto.getDescription());
        privilege.setCode(privilegeDto.getCode());
        privilege.set_active(privilegeDto.is_active());

        Privilege updatedPrivilege = this.privilegeRepository.save(privilege);
        return this.modelMapper.map(updatedPrivilege, PrivilegeDto.class);

    }

    @Override
    public PrivilegeDto getPrivilege(Integer privilegeId) {
        Privilege privilege = this.privilegeRepository.findById(privilegeId).orElseThrow();
        return this.modelMapper.map(privilege, PrivilegeDto.class);
    }

    @Override
    public List<PrivilegeDto> getAllPrivilege() {
        List<Privilege> privileges = this.privilegeRepository.findAll();
        List<PrivilegeDto> privilegesDto = privileges.stream().map(privilege -> this.modelMapper.map(privilege, PrivilegeDto.class)).collect(Collectors.toList());
        return privilegesDto;
    }

    @Override
    public void deletePrivilege(Integer privilegeId) {
        Privilege privilege = this.privilegeRepository.findById(privilegeId).orElseThrow();
        this.privilegeRepository.delete(privilege);
    }
}
