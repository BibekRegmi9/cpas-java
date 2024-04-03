package org.example.cpas.services.impl;
import org.example.cpas.dto.UserDto;
import org.example.cpas.entities.Role;
import org.example.cpas.entities.User;
import org.example.cpas.entities.UserRoleMapping;
import org.example.cpas.repository.RoleRepository;
import org.example.cpas.repository.UserRepository;
import org.example.cpas.repository.UserRoleMappingRepository;
import org.example.cpas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleMappingRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    @Transactional
    public UserDto createUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepository.save(user);

        for(Integer roleId: userDto.getRoleId()){

           Optional<Role> optionalRole= this.roleRepository.findById(roleId);
            Role role = optionalRole.orElse(null);
            if(role==null){
               throw new RuntimeException("Empty role");
           }else{
               UserRoleMapping userRole = new UserRoleMapping();
               userRole.setUser(savedUser);
               userRole.setRole(role);
               userRoleRepository.save(userRole);
           }
        }

        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setCitizenNo(userDto.getCitizenNo());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.set_active(userDto.is_active());

        User updatedUser =this.userRepository.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        this.userRepository.delete(user);
    }

}
