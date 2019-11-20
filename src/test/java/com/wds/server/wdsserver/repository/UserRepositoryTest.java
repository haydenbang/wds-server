package com.wds.server.wdsserver.repository;

import com.wds.server.wdsserver.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableJpaRepositories("com.wds.server.wdsserver.repository")
@EntityScan("com.wds.server.wdsserver.domain")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void whenGetLookUp김터디_thenReturnList(){  // TODO FAIL
        String targetName = "김터디";
        User user1 = new User();
        user1.setIdx(1L);
        user1.setName("김터디");

        User user2 = new User();
        user2.setIdx(2L);
        user2.setName("스터디");

        User user3 = new User();
        user3.setIdx(3L);
        user3.setName("김터디");

        mockUserRepository.saveAll(Arrays.asList(user1, user2, user3));

        List<User> lookUpUsers = mockUserRepository.lookUpUsers("", "김터디");
        assertEquals(2, lookUpUsers.size());
    }
}
