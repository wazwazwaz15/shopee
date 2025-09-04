package org.weiga.shopee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.weiga.shopee.dto.UserCreateRequest;
import org.weiga.shopee.dto.UserLoginRequest;
import org.weiga.shopee.model.ShopeeUser;
import org.weiga.shopee.model.UserInfo;
import org.weiga.shopee.service.JwtService;
import org.weiga.shopee.service.impl.ShopeeUserDetailService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper mapper = new ObjectMapper();


    @Test
    @Transactional
    public void register_success() throws Exception {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("yen");
        userCreateRequest.setPassword("testPassword");
        userCreateRequest.setEmail("test2@gmail.com");
        String entity = mapper.writeValueAsString(userCreateRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entity);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.email", equalTo("test2@gmail.com")));
    }


    @Test
    public void login_success() throws Exception {
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test1@mail.com");
        request.setPassword("testPassword1");
        String content = (request);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.user", equalTo("wei")));


    }

    @TestConfiguration
    static class MockConfig {
        private ObjectMapper mapper = new ObjectMapper();

        @Bean
        @Primary
        public ShopeeUserDetailService mockShopeeUserDetailService() throws JsonProcessingException {
            ShopeeUser mockUser = new ShopeeUser();
            mockUser.setUsername("wei");
            mockUser.setPassword("123");
            mockUser.setEmail("test@gmail.com");


            ShopeeUserDetailService mock = Mockito.mock(ShopeeUserDetailService.class);
            Mockito.when(mock.loadUserByUsername(Mockito.any())).thenReturn(new UserInfo(mockUser));
            return mock;
        }

        @Bean
        @Primary
        public JwtService MockjwtService() {
            JwtService mock = Mockito.mock(JwtService.class);
            Mockito.when(mock.auth(Mockito.any())).thenReturn("thisIsMockToken");
            return mock;
        }


    }


}