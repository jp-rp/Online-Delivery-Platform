package com.sep.onlinedeliverysystem.controller;
import com.sep.onlinedeliverysystem.domain.dto.UserDTO;
import com.sep.onlinedeliverysystem.domain.entities.User;
import com.sep.onlinedeliverysystem.mappers.Mapper;
import com.sep.onlinedeliverysystem.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private Mapper<User, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<User, UserDTO> userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //using DTOs to decouple service layer from persistence layer!!!

    @PostMapping(path = "/users")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user){ //Create functionality
        User userEntity = userMapper.mapFrom(user);
        User savedUserEntity = userService.save(userEntity); //saves user DTO as entity into our database
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED); //returns our saved entity as a DTO
    }

    @GetMapping(path = "/users")
    public List<UserDTO> listUsers(){ //Read All functionality
        List<User> users = userService.findAll();
        return users.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{email}") //Read One functionality
    public ResponseEntity<UserDTO> getUser(@PathVariable("email") String email){
        Optional<User> foundUser = userService.findOne(email); //Use optional because either the user exists or it doesn't
        return foundUser.map(userEntity -> { //for if user exists
            UserDTO userDTO = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); //for if user doesn't exist
    }

    @PutMapping(path = "/users/{email}")
    public ResponseEntity<UserDTO> fullUpdateUser(@PathVariable("email") String email, @RequestBody UserDTO userDTO){ //Full Update functionality
        if(!userService.Exists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDTO.setEmail(email);
        User userEntity = userMapper.mapFrom(userDTO);
        User savedUserEntity = userService.save(userEntity); //can reuse our create functionality to overwrite current user's info
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{email}")
    public ResponseEntity<UserDTO> partialUpdate(@PathVariable("email") String email, @RequestBody UserDTO userDTO){ //Partial Update functionality
        if(!userService.Exists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User userEntity = userMapper.mapFrom(userDTO);
        User updatedUser = userService.partialUpdate(email, userEntity);
        return new ResponseEntity<>(userMapper.mapTo(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{email}")
    public ResponseEntity deleteUser(@PathVariable("email") String email){
        userService.delete(email);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
