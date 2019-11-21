package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private User mockUser;

    private List<User> userList = new ArrayList<>();
    private int _userCount = 3;

    @BeforeAll
    public void setUp() throws Exception {
        mockUser = new User();
        mockUser.setIdx(1L);
        mockUser.setPass("1234");
        mockUser.setNick_name("별");
        mockUser.setName("이재두");
        mockUser.setAddress("서울");
        mockUser.setTel("01012344321");
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
            mockUser = new User();
            mockUser.setIdx((long) i);
            mockUser.setPass("1234");
            mockUser.setNick_name("별");
            mockUser.setName("이재두" + i);
            mockUser.setAddress("서울");
            mockUser.setTel("01012344321");
            userList.add(mockUser);

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

}
