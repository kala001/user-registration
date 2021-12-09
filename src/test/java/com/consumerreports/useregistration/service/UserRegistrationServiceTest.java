package com.consumerreports.useregistration.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.consumerreports.useregistration.model.UserDetails;
import com.consumerreports.useregistration.repository.UserDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationServiceTest {

    @Mock
    UserDetailsRepository repo;

    @InjectMocks
    UserRegistrationService service;

    @Test
    public void getAllRegisteredUsersTest() {
        when(repo.findAll()).thenReturn(getUserList());
        List<UserDetails> users = service.getAllRegisteredUsers();
        Assert.notEmpty(users, "users mustn't be empty");
        Assert.isTrue((1 == users.size()), "users should have one element");

    }

    @Test
    public void registerUserTest() {
        UserDetails userinfo = new UserDetails(1l, "firstName", "lastName", "email");
        when(repo.save(any())).thenReturn(userinfo);
        service.registerUser(userinfo);
        Mockito.verify(repo, Mockito.times(1)).save(Mockito.any());

    }

    private List<UserDetails> getUserList() {
        List<UserDetails> users = new ArrayList<>();
        users.add(new UserDetails(1l, "firstName", "lastName", "email"));
        return users;
    }

}
