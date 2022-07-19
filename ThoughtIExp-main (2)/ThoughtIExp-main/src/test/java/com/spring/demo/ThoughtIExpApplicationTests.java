package com.spring.demo;

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
class ThoughtIExpApplicationTests {

	

	@Test
	public void test() {
		AuthRequst authrequst = new AuthRequst();
		authrequst.setUserName("userName");
		authrequst.setPassword("password");
	}

	@Test
	public void test1() {
		Customer customer = new Customer();
		customer.setPassword("password");
		customer.setUsername("uasername");
		customer.setAge(2);
		customer.setMobile("45678");

	}
	
}
