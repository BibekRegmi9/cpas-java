package org.example.cpas.services;

import org.example.cpas.dto.ModuleDto;

import java.util.List;

public interface ModuleService {
    ModuleDto createModule(ModuleDto module);

    ModuleDto updateModule(ModuleDto module, Integer moduleId);

    ModuleDto getModule(Integer moduleId);

    List<ModuleDto> getAllModule();

    void deleteModule(Integer moduleId);
}
