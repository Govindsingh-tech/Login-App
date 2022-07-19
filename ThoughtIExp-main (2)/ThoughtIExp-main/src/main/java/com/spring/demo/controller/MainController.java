package com.spring.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.model.AuthRequst;
import com.spring.demo.model.Customer;
import com.spring.demo.service.UserDetailService;
import com.spring.demo.util.JwtUtil;

@RestController
public class MainController {

	private static final Customer Customer = null;

	@Autowired
	private UserDetailService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationmanager;

	@PostMapping(value="/registration",consumes = {"application/json"})
	public String Registration(@RequestBody Customer customer) {
		userDetailsService.saveCustomer(customer);
		return "Registered Success";
	}

	@PostMapping("/login")
	public String Login(@RequestParam String username, @RequestParam String password) {
		return userDetailsService.login(username, password);
	}

	@GetMapping("/getAll")
	public List<Customer> getAllCustomer() {

		return userDetailsService.getCustomerDetails();
	}

	
	@GetMapping("/")
	public String welcome() {

		return "login success";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequst authRequest) throws Exception {
		try {
			authenticationmanager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("invalid username/Password");
		}

		return jwtUtil.generateToken(authRequest.getUserName());
	}
	
	
}
