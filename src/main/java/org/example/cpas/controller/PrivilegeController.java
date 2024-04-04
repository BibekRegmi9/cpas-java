package org.example.cpas.controller;

import org.example.cpas.dto.PrivilegeDto;
import org.example.cpas.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping("/")
    public ResponseEntity<PrivilegeDto> createPrivilege(@RequestBody PrivilegeDto privilegeDto){
        PrivilegeDto createPrivilegeDto = this.privilegeService.createPrivilege(privilegeDto);
        return new ResponseEntity<PrivilegeDto>(createPrivilegeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{privilegeId}")
    public ResponseEntity<PrivilegeDto> updatePrivilege(@RequestBody PrivilegeDto privilegeDto, @PathVariable("privilegeId") Integer pid){
        PrivilegeDto updatePrivilegeDto = this.privilegeService.updatePrivilege(privilegeDto, pid);
        return new ResponseEntity<PrivilegeDto>(updatePrivilegeDto, HttpStatus.OK);
    }

    @GetMapping("/{privilegeId}")
    public ResponseEntity<PrivilegeDto> getPrivilege(@PathVariable("privilegeId") Integer pid){
        PrivilegeDto getPrivilegeDto = this.privilegeService.getPrivilege(pid);
        return new ResponseEntity<PrivilegeDto>(getPrivilegeDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PrivilegeDto>> getAllPrivilegeDto(){
        List<PrivilegeDto> getAllPrivilegeDto = this.privilegeService.getAllPrivilege();
        return ResponseEntity.ok(getAllPrivilegeDto);
    }

    @DeleteMapping("/{privilegeId}")
    public ResponseEntity<Boolean> deletePrivilege(@PathVariable("privilegeId") Integer pid){
        this.privilegeService.deletePrivilege(pid);
        return (ResponseEntity<Boolean>) ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
