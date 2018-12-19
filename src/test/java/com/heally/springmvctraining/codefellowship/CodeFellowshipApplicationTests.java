package com.heally.springmvctraining.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeFellowshipApplicationTests {

	@Autowired
	private ApplicationUserController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
    public void homePageLoads() {
	    //get request
        //get response
        //not null
        //not error
        //200 family
        //hacky <h1>hello<h1>

    }

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void greetingShouldReturnDefaultMessage() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
//                String.class)).contains("Hello World");
//    }

}

