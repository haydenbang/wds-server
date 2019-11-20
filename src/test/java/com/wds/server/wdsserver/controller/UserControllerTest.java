package com.wds.server.wdsserver.controller;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RestClientTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    private final static String BASE_URI = "/api/v1/users";

    @Mock
    private UserService mockUserService;
    @Mock
    private ResponseType mockResponseType;
    private MockMvc mockMvc;

    @Mock
    private User mockUser;

    @InjectMocks
    private UserController mockUserController;

    @BeforeAll
    public void init(){
        mockUserController = new UserController(mockUserService, mockResponseType);
        mockMvc = MockMvcBuilders.standaloneSetup(mockUserController).build();
    }

    @Test
    public void whenRequestGETBaseURI_thenGetOKStatus() throws Exception {
        List<User> mockUserList = new ArrayList<>(10);

        int idx = 1;
        for(User mockUser : mockUserList){
            mockUser = new User();
            mockUser.setIdx(Integer.toUnsignedLong(idx));
            mockUser.setId("cId"+idx);
            mockUser.setName("cName"+idx);
            mockUser.setPass("cPass"+idx);

            idx++;
        }

        when(mockUserService.getAllUsers()).thenReturn(mockUserList);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void whenRequestGETBaseURIwithQuery_thenGetOKKStatus() throws Exception {
        List<User> mockUserList = new ArrayList<>(3);

        User user1 = new User();
        user1.setId("1111");
        user1.setName("1111");
        user1.setTel("010-5555-55555");

        User user2 = new User();
        user1.setId("1112");
        user1.setName("1112");
        user1.setTel("010-5555-55555");

        mockUserList.add(user1);
        mockUserList.add(user2);

        when(mockUserService.lookUpUsers("", "2")).thenReturn(mockUserList);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI+"/lookup")
                        .param("query","2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void whenRequestPOSTBaseURI_thenGetOKStatus() throws Exception { // TODO 400 error
        mockUser = new User();
//        mockUser.setIdx(Integer.toUnsignedLong(idx));
        mockUser.setIdx(1L);
        mockUser.setId("JKH");
        mockUser.setPass("1234");
        mockUser.setName("지경희");

        ObjectMapper mapper = new ObjectMapper();
        String mockUserJson = mapper.writeValueAsString(mockUser);

        when(mockUserService.addUser(Mockito.any(User.class))).thenReturn(mockUser);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI)
                        .content(mockUserJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void whenGetUserIdx1_thenGetOKStatus() throws Exception {
        mockUser = new User();
        int idx = 1;
        mockUser.setIdx(Integer.toUnsignedLong(idx));
        mockUser.setId("JKH");
        mockUser.setName("지경희");
        mockUser.setPass("1234");

        when(mockUserService.getUser(Mockito.anyLong())).thenReturn(mockUser);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI+"/"+idx))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("지경희"));
//                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void whenDeleteUserIdx1_thenGetOKStatus() throws Exception{
        int idx = 1;

        doNothing().when(mockUserService).deleteUser(Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URI+"/"+idx))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
