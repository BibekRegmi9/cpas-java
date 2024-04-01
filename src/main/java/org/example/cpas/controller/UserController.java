package org.example.cpas.controller;

import org.example.cpas.dto.UserDto;
import org.example.cpas.entities.User;
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
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto  createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //Put-update
    @PostMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updateUserDto = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updateUserDto);
    }

    @PostMapping("/")
    ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/{userId}")
    ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return null;
    }

}
