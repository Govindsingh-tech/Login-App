package com.spring.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.demo.model.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	Customer findByUsername(String username);
	

	Customer findByUsernameAndPassword(String username, String password);

}
