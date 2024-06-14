package com.angular.grupo8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.angular.grupo8.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Procedure(name = "User.getAllUsers")
    List<User> getAllUsers();
}
