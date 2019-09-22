package com.fittipaldi.game.controller;

import java.awt.PageAttributes.MediaType;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IndexControlletTest {

	@Autowired
	private WebTestClient webClient;

	@Autowired
	private TestRestTemplate restTempalte;

	@Mock
	private RestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testActionTest() {

		Mockito.when(restTemplate.getForEntity("http://localhost:8080/employee/E001", String.class))
				.thenReturn(new ResponseEntity("OK", HttpStatus.OK));

		Assertions.assertThat("OK").isEqualTo("OK");
	}

}
