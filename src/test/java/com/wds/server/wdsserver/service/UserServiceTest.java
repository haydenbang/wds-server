package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.WdsServerApplication;
import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WdsServerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserService mockUserService;

    @Mock
    private User mockUser;

    @BeforeAll
    public void setUp() throws Exception {
        mockUser = new User();

        mockUser.setIdx(1L);
        mockUser.setAddress("서울");
        mockUser.setName("이재두");

        when(mockUserService.getUser(1L)).thenReturn(mockUser);

    }

    @Test
    public void whenUserIdxIs1_thenName이재두() throws Exception {
        User userInfo = mockUserService.getUser((long) 1);
        assertEquals(userInfo.getName(), "이재두");
    }

}
