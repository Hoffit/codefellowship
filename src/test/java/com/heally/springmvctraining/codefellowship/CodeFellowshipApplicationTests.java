package com.heally.springmvctraining.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeFellowshipApplicationTests {

    @LocalServerPort
    private int port;

	@Autowired
	private ApplicationUserController controller;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
    public void getHomeWorks() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("The Code Fellows Fellowship");
    }

    @Test
    public void getLoginWorks() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Enter your username and password");
    }

    @Test
    public void getRegisterWorks() {
	    String requestResult = restTemplate.getForObject("http://localhost:" + port + "/register", String.class);
        assertThat(requestResult.contains("to register a new account") ||
                requestResult.contains("Create a new account"));
    }
}

