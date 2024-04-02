package org.example.cpas.controller;

import jakarta.validation.Valid;
import org.example.cpas.dto.RoleDto;
import org.example.cpas.dto.UserDto;
import org.example.cpas.services.RoleService;
import org.example.cpas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto){
        RoleDto createRole = this.roleService.createRole(roleDto);
        return new ResponseEntity<RoleDto>(createRole, HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto, @PathVariable("roleId") Integer rid){
        RoleDto updateRole = this.roleService.updateRole(roleDto, rid);
        return new ResponseEntity<RoleDto>(updateRole, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<RoleDto> getRole(@PathVariable("roleId") Integer rid){
        RoleDto getRole = this.roleService.getRole(rid);
        return new ResponseEntity<RoleDto>(getRole, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        List<RoleDto> getAllRole =  this.roleService.getAllRoles();
        return ResponseEntity.ok(getAllRole);
    }

    @DeleteMapping("/")
    public boolean ResponseEntity(@PathVariable("roleId") Integer rid){
        this.roleService.deleteRole(rid);
        return true;
    }
}
