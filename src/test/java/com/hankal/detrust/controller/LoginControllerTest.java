package com.hankal.detrust.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
//@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = MockServletContext.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class LoginControllerTest extends MockMvcResultMatchers {

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    private MockMvc mvc;

    @Test
    public void login() throws Exception {

        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("/logins");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        System.out.println("UserControllerTest.testUserController().get");
    }
}