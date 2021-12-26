package com.example.demo.services.implementations;

import com.example.demo.database.entity.User;
import com.example.demo.database.repo.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = (ArrayList<User>)userRepository.findAll(Sort.by("id"));
        return users;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
