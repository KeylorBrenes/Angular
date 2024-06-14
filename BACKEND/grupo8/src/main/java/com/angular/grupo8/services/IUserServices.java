package com.angular.grupo8.services;

import java.util.List;

import com.angular.grupo8.domain.User;

public interface IUserServices {

    List<User> getAllActiveUsers();

    User getUserById(Long id);
}
