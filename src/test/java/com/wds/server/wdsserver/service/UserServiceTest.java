package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
@RestClientTest
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

    @BeforeAll
    public void setUp() {
        mockUser1 = new User();

        mockUser1.setIdx(1L);
        mockUser1.setName("이재두");
        mockUser1.setTel("010-1111-2222");

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
    public void callAllUserOrderByCreatedDate() throws Exception {
        mockUserService.getAllUsersOrderByCreatedDate();
        verify(mockUserRepository, times(1)).getAllUsersOrderByCreatedDate();
    }

    @Test
    public void whenUserIdxIs1_thenName이재두() throws Exception {
        User userInfo = mockUserService.getUser((long) 1);
        verify(mockUserRepository, times(1)).findById(1L);
        assertEquals("이재두", userInfo.getName());
    }

    @Test
    public void whenGetLookUpUsersQuery2_thenReturnUserList(){
        List<User> lookUpUsers = mockUserService.lookUpUsers("", "2");
        verify(mockUserRepository, times(1)).lookUpUsers("", "2");
        assertEquals(mockUserList, lookUpUsers);
    }

}
