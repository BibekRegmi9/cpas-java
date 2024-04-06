package org.example.cpas.controller;

import org.example.cpas.dto.ModuleDto;
import org.example.cpas.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/")
    public ResponseEntity<ModuleDto> createModule(@RequestBody ModuleDto moduleDto){
        ModuleDto createModuleDto = this.moduleService.createModule(moduleDto);
        return new ResponseEntity<ModuleDto>(createModuleDto, HttpStatus.CREATED);
    }



}
