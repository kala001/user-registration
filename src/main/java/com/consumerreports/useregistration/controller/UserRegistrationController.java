package com.consumerreports.useregistration.controller;

import static com.consumerreports.useregistration.constants.UserRegistrationConstants.ERROR_INDICATOR;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.NEW_USER_REGISTRATION_MAPPING;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.NO_USERS_INDICATOR;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.REGISTER_STATUS_PAGE;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.REGISTER_USER_PAGE;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.SUCCESS_INDICATOR;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.USERS_DETAILS;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.USER_REGISTRATION_MAPPING;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.VALIDATION_ERROR_INDICATOR;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.VIEW_REGISTERED_USERS_MAPPING;
import static com.consumerreports.useregistration.constants.UserRegistrationConstants.VIEW_USER_PAGE;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consumerreports.useregistration.model.UserDetails;
import com.consumerreports.useregistration.service.UserRegistrationService;
/**
 * Controller class for User Registration
 * 
 * @author kala
 *
 */

@Controller
@RequestMapping(USER_REGISTRATION_MAPPING)
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    /**
     * This method get the List of registered users and send the list to
     * view-users.jsp to plot the same.
     * 
     * @param model
     * @return String
     */
    @GetMapping(VIEW_REGISTERED_USERS_MAPPING)
    public String getAllRegisterdUsers(Model model) {
        try {
            List<UserDetails> list = userRegistrationService.getAllRegisteredUsers();
            if (list.size() < 1) {
                model.addAttribute(NO_USERS_INDICATOR, true);
            } else {
                model.addAttribute(SUCCESS_INDICATOR, true);
                model.addAttribute(USERS_DETAILS, list);

            }
        } catch (Exception ex) {
            model.addAttribute(ERROR_INDICATOR, true);
        }

        return VIEW_USER_PAGE;
    }

    /**
     * This Method will load the register-user.jsp where users can register.
     * 
     * @param model
     * @return String
     */
    @GetMapping(NEW_USER_REGISTRATION_MAPPING)
    public String register(Model model) {
        model.addAttribute("userDetails", new UserDetails());
        return REGISTER_USER_PAGE;
    }

    /**
     * This Method will save the user details to database and redirects user to
     * view-result.jsp where user can either register another user or see the list
     * of registered users.
     * 
     * @param model
     * @return String
     */
    @PostMapping(NEW_USER_REGISTRATION_MAPPING)
    public String registerUser(@ModelAttribute("userDetails") @Valid UserDetails userDetails, Model model) {
        try {
            if (userDetails != null && validate(userDetails) && validateEmail(userDetails.getEmail())) {
                userRegistrationService.registerUser(userDetails);
                model.addAttribute(SUCCESS_INDICATOR, true);
            } else {
                model.addAttribute(VALIDATION_ERROR_INDICATOR, true);
                model.addAttribute("userDetails", new UserDetails());
                return REGISTER_USER_PAGE;
            }
           
        } catch (Exception ex) {
            model.addAttribute(ERROR_INDICATOR, true);
        }
        return REGISTER_STATUS_PAGE;
    }
    
    private boolean validate(UserDetails userDetails) {
        return (StringUtils.hasLength(userDetails.getEmail()) && StringUtils.hasLength(userDetails.getFirstName()) && StringUtils.hasLength(userDetails.getLastName()));
    }
    
    private boolean validateEmail(String email) {
        
        String regex = "^(.+)@(.+)$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
