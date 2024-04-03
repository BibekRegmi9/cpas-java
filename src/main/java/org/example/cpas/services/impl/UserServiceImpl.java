package org.example.cpas.services.impl;
import org.example.cpas.converter.DtoConverter;
import org.example.cpas.dto.RoleDto;
import org.example.cpas.dto.UserDto;
import org.example.cpas.entities.Role;
import org.example.cpas.entities.User;
import org.example.cpas.entities.UserRoleMapping;
import org.example.cpas.repository.RoleRepository;
import org.example.cpas.repository.UserRepository;
import org.example.cpas.repository.UserRoleMappingRepository;
import org.example.cpas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
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

        for(RoleDto dto: userDto.getRoleDtos()){
           Optional<Role> optionalRole= this.roleRepository.findById(dto.getId());
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

        return userDto;
    }



    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Integer userId) {
        // check if user exist or not
        User existUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("The User with id: %d not found.", userId)));
        // update user
        existUser.setName(userDto.getName());
        existUser.setEmail(userDto.getEmail());
        existUser.setGender(userDto.getGender());
        existUser.setCitizenNo(userDto.getCitizenNo());
        existUser.setPhone(userDto.getPhone());
        existUser.setPassword(userDto.getPassword());
        existUser.set_active(userDto.is_active());
        userRepository.save(existUser);

//        List<int> existRoleList=this.userRoleRepository.findAll()
        Integer[] oldRoleId = this.userRoleRepository.getAllRoleByUserId(userId);
        Integer[] newRoleId = userDto.getRoleDtos()
                .stream()
                .map(m -> Integer.valueOf(m.getId()))
                .toArray(Integer[]::new);

        Integer []  insertableRole= Arrays.stream(newRoleId).filter(f->{
            System.out.println(f);
            return !Arrays.asList(oldRoleId).contains(f);
        }).toArray(Integer[]::new);

        Integer []  deletableArrayList= Arrays.stream(oldRoleId).filter(f->{
            return !Arrays.asList(newRoleId).contains(f);
        }).toArray(Integer[]::new);


        // delete roles
        this.userRoleRepository.deleteMapping(userId,deletableArrayList);

        Arrays.stream(insertableRole).forEach(f->{
            UserRoleMapping urm= new UserRoleMapping();
            Role role=this.roleRepository.findById(f).orElseThrow();
            urm.setUser(existUser);
            urm.setRole(role);
            this.userRoleRepository.save(urm);
        });
        return  userDto;
    }

    @Override
    public UserDto getById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        return this.modelMapper.map(user, UserDto.class);
    }



//    @Override
//    public List<UserDto> getAllUsers() {
//        List<User> users = this.userRepository.findAll();
//        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//        return userDtos;
//    }



    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        this.userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(DtoConverter::convert).collect(Collectors.toList());
    }

}
