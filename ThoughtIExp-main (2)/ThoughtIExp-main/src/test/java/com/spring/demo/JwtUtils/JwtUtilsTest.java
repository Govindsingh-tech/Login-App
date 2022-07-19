package com.spring.demo.JwtUtils;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.demo.util.JwtUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilsTest {

	public MockMvc mockMvc;

	@Autowired
	public JwtUtil jwtUtil;

	@Test
	public String validateTokenTest() {

		String token = "amvnawefuf2u39ofin23fnkcne292onc98ch2";
		return token;

	}

}
