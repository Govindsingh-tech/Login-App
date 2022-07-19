package com.spring.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class MainControllerTest {

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
	public void registrationTest() throws Exception {
		Customer customer = new Customer();
		customer.setPassword("password");
		customer.setUsername("username");
		customer.setAge(2);
		customer.setMobile("45678");
		ObjectMapper obj = new ObjectMapper();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/registration";
		String jsoRequest = obj.writeValueAsString(customer);
		MvcResult mvcResult = mockMvc.perform(post(uri).content(jsoRequest).accept(MediaType.APPLICATION_JSON_VALUE).header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1lIiwiZXhwIjoxNjU4MjE1MzI3LCJpYXQiOjE2NTgxNzkzMjd9.8TPCqdShvR4Cue9geomgpHGAXD9HRIolfu9q5cssH80").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(resultContent.equals("Registered Success"));

	}
	
	@Test
	public void generateTokenTest() throws Exception {
		AuthRequst authRequst = new AuthRequst();
		authRequst.setPassword("password");
		authRequst.setUserName("name");
		ObjectMapper obj = new ObjectMapper();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/authenticate";
		String jsoRequest = obj.writeValueAsString(authRequst);
		MvcResult mvcResult = mockMvc.perform(post(uri).content(jsoRequest).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(resultContent.equals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1lIiwiZXhwIjoxNjU4MjE1MzI3LCJpYXQiOjE2NTgxNzkzMjd9.8TPCqdShvR4Cue9geomgpHGAXD9HRIolfu9q5cssH80"));

	}
	
	@Test
	public void getProductsList() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/getAll";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Customer[] productlist = mapFromJson(content, Customer[].class);
//		assertTrue(productlist.length > 0);
	}

	@Test
	public void welcomePageTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void LoginTest() {
		String username = "name";
		String password = "password";
		when(customerrepo.findByUsernameAndPassword(username, password))
		.thenReturn(new Customer(1, "name", "password", "9876543", 12));
		assertNotNull(userDetailsService.login(username, password));
		
	}

	
}
