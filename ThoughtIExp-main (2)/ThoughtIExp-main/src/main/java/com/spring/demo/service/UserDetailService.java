package com.spring.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.demo.model.Customer;


public interface UserDetailService {
	
	public Customer saveCustomer(Customer customer);
	
	Customer findByUserName(String username);

	public String login(String username, String password); 
	
	List<Customer> getCustomerDetails();

	String updatePassword(String username, String password);

//	Customer findByUserName1(String username);
//
//	Customer findByUserName11(String username);
	
	
}
