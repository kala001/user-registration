package com.consumerreports.useregistration.repository;

import org.springframework.data.repository.CrudRepository;
/**
 * User details repository to interact with database for user_details table
 */

import com.consumerreports.useregistration.model.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

}
