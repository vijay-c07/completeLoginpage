package com.company.loginapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.company.loginapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username); // custom query
}