package com.example.demo.services;

import com.example.demo.database.entity.User;

import java.util.ArrayList;

public interface UserService {
    public void saveUser(User user);
    public ArrayList<User> getAllUsers();
    public User getUserById(int id);
    public void deleteUser(int id);

}
