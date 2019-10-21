package com.wds.server.wdsserver.service.impl;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import com.wds.server.wdsserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User userDetail) {
        User user = userRepository.findById(userDetail.getId()).get();
        user.setName(userDetail.getName());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        try {
            User user = userRepository.findById(userId).get();
            userRepository.delete(user);

        } catch (Exception e) {
            log.info("Error");
            throw new Exception();
        }
    }
}
