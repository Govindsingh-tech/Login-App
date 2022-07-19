package com.spring.demo.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.demo.controller.MainController;
import com.spring.demo.model.AuthRequst;
import com.spring.demo.model.Customer;
import com.spring.demo.repo.CustomerRepo;
import com.spring.demo.service.UserDetailService;
import com.spring.demo.util.JwtUtil;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceImplTest {

	public MockMvc mockMvc;

	@Autowired
	public JwtUtil jwtUtil;

	@Test
	void contextLoads() {

	}

	@InjectMocks
	private MainController mainController;

	@InjectMocks
	private AuthRequst authRequest;
	@InjectMocks
	private Customer customer;

	@Autowired
	private UserDetailService userDetailsService;

	@MockBean
	private CustomerRepo customerrepo;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	public void getCustomerDetailsTest() {
		when(customerrepo.findAll())
				.thenReturn(Stream.of(new Customer(1, "name", "password", "9876543", 12)).collect(Collectors.toList()));
		assertEquals(1, userDetailsService.getCustomerDetails().size());
	}

	@Test
	public void findByUserNameTest() {
		String username = "name";
		when(customerrepo.findByUsername(username)).thenReturn(new Customer(1, "name", "password", "9876543", 12));
		assertNotNull(userDetailsService.findByUserName(username));
	}

	@Test
	public void loginTest() {
		String username = "name";
		String password = "password";
		when(customerrepo.findByUsernameAndPassword(username, password))
				.thenReturn(new Customer(1, "name", "password", "9876543", 12));
		assertNotNull(userDetailsService.login(username, password));
	}

	@Test
	public void updatePasswordTest() {
		String username = "name";
		String password = "password";
		when(customerrepo.save(new Customer(1, "name", "password", "9876543", 12)))
				.thenReturn(new Customer(1, "name", "password", "9876543", 12));
		assertNotNull(userDetailsService.updatePassword(username, password));
	}
	
	@Test
	public void findByUsernameAndPasswordTest() {
		
		String username = "name";
		String password = "password";
		when(customerrepo.findByUsernameAndPassword(username, password)).thenReturn(customer);
		
	}
	
	@Test
	public void saveCustomer() throws Exception {
		
		Customer cust = new Customer();
		cust.setUsername("name1");
		cust.setPassword("password");
		cust.setAge(15);
		cust.setMobile("9875432");
		
		ObjectMapper obj = new ObjectMapper();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/registration";
		String jsoRequest = obj.writeValueAsString(customer);
		MvcResult mvcResult = mockMvc.perform(post(uri).content(jsoRequest).accept(MediaType.APPLICATION_JSON_VALUE).header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1lIiwiZXhwIjoxNjU4MjE1MzI3LCJpYXQiOjE2NTgxNzkzMjd9.8TPCqdShvR4Cue9geomgpHGAXD9HRIolfu9q5cssH80").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(resultContent.equals("Registered Success"));
		
	}

}
