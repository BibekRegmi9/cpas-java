package org.example.cpas.controller;

import jakarta.validation.Valid;
import org.example.cpas.dto.UserDto;
import org.example.cpas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Post-create
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto  createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
    }

    //Put-update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updateUserDto = this.userService.updateUser(userDto, uid);
        return new ResponseEntity<UserDto>(updateUserDto, HttpStatus.OK);
    }

    //get-all
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users =this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid){
        UserDto user = this.userService.getById(uid);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return true;
    }

}
