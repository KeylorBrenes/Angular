package com.angular.grupo8.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angular.grupo8.domain.User;
import com.angular.grupo8.repository.UserRepository;
import com.angular.grupo8.services.IUserServices;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UserService implements IUserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAllActiveUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}