package com.example.demo.controllers;

import com.example.demo.database.entity.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ArrayList<User> getAll(){
        try{
            return userService.getAllUsers();
        }catch (NoSuchElementException e){
            return null;
        }
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        try {
            return userService.getUserById(id);
        }catch (NoSuchElementException e){
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
