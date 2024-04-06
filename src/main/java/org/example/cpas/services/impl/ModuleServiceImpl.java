package org.example.cpas.services.impl;

import org.example.cpas.dto.ModuleDto;
import org.example.cpas.entities.Module;
import org.example.cpas.entities.ModulePrivilegeMapping;
import org.example.cpas.entities.Privilege;
import org.example.cpas.entities.Screen;
import org.example.cpas.repository.ModulePrivilegeMappingRepository;
import org.example.cpas.repository.ModuleRepository;
import org.example.cpas.repository.PrivilegeRepository;
import org.example.cpas.repository.ScreenRepository;
import org.example.cpas.services.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModulePrivilegeMappingRepository modulePrivilegeMappingRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public ModuleDto createModule(ModuleDto moduleDto) {
        Module module = this.modelMapper.map(moduleDto, Module.class);
        Screen screen = this.screenRepository.findById(moduleDto.getScreen_id()).orElseThrow(()->new RuntimeException("Screen not found"));
//            moduleDto.setScreenId(moduleDto.getScreenId());

        module.setScreen(screen);
        Module savedModule = this.moduleRepository.save(module);

        for(ModuleDto.PrivilegeDto mp: moduleDto.getPrivilege()){
            if(mp != null){
                Privilege privilege=this.privilegeRepository.findById(mp.getPrivilegeId()).orElse(null);
                if(privilege!=null){
                    ModulePrivilegeMapping createModulePrivilege = new ModulePrivilegeMapping();
                    createModulePrivilege.setPrivilege(privilege);
                    createModulePrivilege.setModule(savedModule);
                    this.modulePrivilegeMappingRepository.save(createModulePrivilege);
                }
            }
        }
        return moduleDto;
    }

    @Override
    public ModuleDto updateModule(ModuleDto module, Integer moduleId) {
        return null;
    }

    @Override
    public ModuleDto getModule(Integer moduleId) {
        return null;
    }

    @Override
    public List<ModuleDto> getAllModule() {
        return null;
    }

    @Override
    public void deleteModule(Integer moduleId) {

    }
}
