package com.github.attacktive.msaproduct;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsaProductApplicationTests {
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(objectMapper);
	}
}
