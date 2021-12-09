package com.consumerreports.useregistration.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.consumerreports.useregistration.model.UserDetails;
import com.consumerreports.useregistration.service.UserRegistrationService;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationControllerTest {

    @Mock
    UserRegistrationService service;

    @InjectMocks
    UserRegistrationController userRegistrationController;

    @Test
    public void getAllRegisteredUsersTest() {
        when(service.getAllRegisteredUsers()).thenReturn(getUserList());
        Model model = new ExtendedModelMap();
        String response = userRegistrationController.getAllRegisterdUsers(model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("view-users"), "response should be view-users");
        Assert.isTrue((boolean) model.getAttribute("success"), "users should be true");
    }

    @Test
    public void getAllRegisteredUsersWithNoUsersTest() {
        when(service.getAllRegisteredUsers()).thenReturn(new ArrayList<>());
        Model model = new ExtendedModelMap();
        String response = userRegistrationController.getAllRegisterdUsers(model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("view-users"), "response should be view-users");
        Assert.isTrue((boolean) model.getAttribute("nousers"), "users should be true");
    }

    @Test
    public void getAllRegisteredUsersWithErrorTest() {
        when(service.getAllRegisteredUsers()).thenThrow(new RuntimeException());
        Model model = new ExtendedModelMap();
        String response = userRegistrationController.getAllRegisterdUsers(model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("view-users"), "response should be view-users");
        Assert.isTrue((boolean) model.getAttribute("error"), "users should be true");
    }

    @Test
    public void registerUserTest() {
        doNothing().when(service).registerUser(any());
        Model model = new ExtendedModelMap();
        UserDetails userinfo = new UserDetails(1l, "firstName", "lastName", "email@email.com");
        String response = userRegistrationController.registerUser(userinfo, model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("view-result"), "response should be view-result");
        Assert.isTrue((boolean) model.getAttribute("success"), "success should be true");
    }
    
    @Test
    public void registerUserVAlidationErrorTest() {
        Model model = new ExtendedModelMap();
        UserDetails userinfo = new UserDetails(1l, "firstName", "", "email");
        String response = userRegistrationController.registerUser(userinfo, model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("register-user"), "response should be register-user");
        Assert.isTrue((boolean) model.getAttribute("validationError"), "validationError should be true");
    }
    
    @Test
    public void registerUserEmailVAlidationErrorTest() {
        Model model = new ExtendedModelMap();
        UserDetails userinfo = new UserDetails(1l, "firstName", "lastName", "email");
        String response = userRegistrationController.registerUser(userinfo, model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("register-user"), "response should be register-user");
        Assert.isTrue((boolean) model.getAttribute("emailValidationError"), "emailValidationError should be true");
    }

    @Test
    public void registerUserWithErrorTest() {
        doThrow(new RuntimeException()).when(service).registerUser(any());
        Model model = new ExtendedModelMap();
        UserDetails userinfo = new UserDetails(1l, "firstName", "lastName", "email@email.com");
        String response = userRegistrationController.registerUser(userinfo, model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("view-result"), "response should be view-result");
        Assert.isTrue((boolean) model.getAttribute("error"), "error should be true");
    }

    @Test
    public void registerTest() {
        Model model = new ExtendedModelMap();
        String response = userRegistrationController.register(model);
        Assert.notNull(response, "response mustn't be empty");
        Assert.isTrue(response.equals("register-user"), "response should be register-user");
        Assert.notNull(model.getAttribute("userDetails"), "userDetails should not be null");
    }

    private List<UserDetails> getUserList() {
        List<UserDetails> users = new ArrayList<>();
        users.add(new UserDetails(1l, "firstName", "lastName", "email"));
        return users;
    }

}
