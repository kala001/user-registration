package com.consumerreports.useregistration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumerreports.useregistration.model.UserDetails;
import com.consumerreports.useregistration.repository.UserDetailsRepository;

/**
 * Service class for User Registration
 * 
 * @author kala
 *
 */
@Service
public class UserRegistrationService {

    @Autowired
    private UserDetailsRepository userRepo;

    public List<UserDetails> getAllRegisteredUsers() {
        List<UserDetails> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    public void registerUser(UserDetails userInfo) {
        userRepo.save(userInfo);
    }

}
