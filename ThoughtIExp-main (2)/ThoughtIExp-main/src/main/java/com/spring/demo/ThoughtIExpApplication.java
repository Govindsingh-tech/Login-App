package com.spring.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.demo.model.Customer;
import com.spring.demo.repo.CustomerRepo;

@SpringBootApplication
public class ThoughtIExpApplication {

	@Autowired
	private CustomerRepo customerRepo;

	@PostConstruct
	public void initusers() {
		List<Customer> cusObj = Stream.of(new Customer(1, "name", "password", "9876543", 12)).collect(Collectors.toList());
		customerRepo.saveAll(cusObj);
	}

	public static void main(String[] args) {
		SpringApplication.run(ThoughtIExpApplication.class, args);
	}    

}
