package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @InjectMocks
    private UserService mockUserService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private User mockUser1;

    @Mock
    private User mockUser2;

    @Mock
    private List<User> mockUserList;

    private List<User> userList = new ArrayList<>();
    private int _userCount = 3;

    @BeforeAll
    public void setUp() {
        mockUser1 = new User();

        mockUser1.setIdx(1L);
        mockUser1.setPass("1234");
        mockUser1.setTel("010-1111-2222");
        mockUser1.setNick_name("별");
        mockUser1.setName("이재두");
        mockUser1.setAddress("서울");
        mockUser1.setTel("01012344321");

        mockUser2 = new User();
        mockUser2.setIdx(2L);
        mockUser2.setName("지경희");
        mockUser2.setTel("010-1111-3333");

        mockUserList = new ArrayList<>();
        mockUserList.add(mockUser1);
        mockUserList.add(mockUser2);

        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser1));
        when(mockUserRepository.lookUpUsers("","2")).thenReturn(mockUserList);
    }

    @Test
    @Order(1)
    public void callAllUserOrderByCreatedDate() throws Exception {
        mockUserService.getAllUsersOrderByCreatedDate();
        verify(mockUserRepository, times(1)).getAllUsersOrderByCreatedDate();
    }

    @Test
    @Order(3)
    public void whenUserIdxIsIdx_thenName이재두Idx() throws Exception {
        for(int i = 1; i <= _userCount; i++) {
            mockUser1 = new User();
            mockUser1.setIdx((long) i);
            mockUser1.setPass("1234");
            mockUser1.setNick_name("별");
            mockUser1.setName("이재두" + i);
            mockUser1.setAddress("서울");
            mockUser1.setTel("01012344321");
            userList.add(mockUser1);

            when(mockUserRepository.findById((long) i)).thenReturn(Optional.ofNullable(userList.get(i - 1)));
        }
        for(int idx = 1; idx <= _userCount; idx++) {
            Optional<User> mockUserList = mockUserRepository.findById((long) idx);
            assertEquals("이재두" + idx, mockUserList.get().getName());
        }
    }

    @Test
    @Order(2)
    public void whenUserIdxIsNull_thenNameNull() throws Exception {
        when(mockUserRepository.findById(2L)).thenReturn(null);
        assertEquals(null, mockUserRepository.findById(2L));
    }

    @Test
    public void whenGetLookUpUsersQuery2_thenReturnUserList(){
        List<User> lookUpUsers = mockUserService.lookUpUsers("", "2");
        verify(mockUserRepository, times(1)).lookUpUsers("", "2");
        assertEquals(mockUserList, lookUpUsers);
    }

}
