package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.WdsServerApplication;
import com.wds.server.wdsserver.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WdsServerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeAll
    public void setUp() throws Exception {
        User user = new User();

        user.setIdx(1L);
        user.setAddress("서울");
        user.setName("이재두");

        when(userService.getUser(1L)).thenReturn(user);

    }

    @Test
    public void getUserNameByIdx() throws Exception {
        User userInfo = userService.getUser((long) 1);
        assertEquals(userInfo.getName(), "이재두");

    }

}
