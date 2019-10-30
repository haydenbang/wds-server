package com.wds.server.wdsserver.service.impl;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import com.wds.server.wdsserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        User user = new User();

        Optional<User> optionalUser = userRepository.findById(userDetail.getIdx());

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        user.setName(userDetail.getName());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idx) throws Exception {
        try {
            Optional<User> optionalUser = userRepository.findById(idx);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                userRepository.delete(user);
            } else {
                log.info("존재하지않는 유저입니다.");
                throw new Exception(); // TODO 400 Error인데, Exception을 던져야하는지?
            }

        } catch (Exception e) {
            log.info("Error");
            throw new Exception();
        }
    }

    @Override
    public User getUser(Long idx) throws Exception {
        Optional<User> optionalUser = userRepository.findById(idx);

        User user= new User();

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            log.info("존재하지않는 유저입니다.");
            throw new Exception(); // TODO 400 Error인데, Exception을 던져야하는지?
        }

        return user;
    }
}
