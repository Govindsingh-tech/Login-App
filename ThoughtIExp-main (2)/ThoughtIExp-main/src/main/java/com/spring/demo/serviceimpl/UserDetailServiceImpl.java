package com.spring.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.demo.model.Customer;
import com.spring.demo.repo.CustomerRepo;
import com.spring.demo.service.UserDetailService;

import CustomerSales.PasswordHashing;

@Service
public class UserDetailServiceImpl implements UserDetailsService,UserDetailService {

	@Autowired
	private CustomerRepo customerRepo;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByUsername(username);
		if(customer!=null) {
			return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
	
		}
		return null;

	}
	public Customer saveCustomer(Customer customer) {
		customer.setPassword(PasswordHashing.hasPassword(customer.getPassword()));
		return customerRepo.save(customer);
	}
	public List<Customer> getCustomerDetails(){
		List<Customer> customerList = customerRepo.findAll();
		return customerList;
	}
	@Override
	public Customer findByUserName(String username) {
		Customer customerList = customerRepo.findByUsername(username);
		return customerList;
	}
//	@Override
//	public Customer findByUserName(String username) {
//		Customer customerList = customerRepo.findByUsername(username);
//		return customerList;
//	}
	
	
//	@Override
//	public String login(String username, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String login(@RequestParam String username, String password) {

		System.out.println("password->>>>>>>" + password);
		Customer customer = customerRepo.findByUsername(username);

		if (customer != null) {
			String stored_hash = customer.getPassword();
			
			if (PasswordHashing.checkPassword(password, stored_hash))
				return "Logged in successfully";
			else
				return "Failed to Login";
		}
		return "Failed to Login";
	}
	
	@Override
	public String updatePassword(String username, String password) {
		System.out.println("password->>>>>>>" + password);
		Customer customer = customerRepo.findByUsername(username);

		if (customer != null) {
			customer.setPassword(PasswordHashing.hasPassword(customer.getPassword()));

				return "Password Updated successfully";
		}
		return "Failed to Update Password";
	}
//	@Override
//	public Customer findByUserName1(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Customer findByUserName11(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
