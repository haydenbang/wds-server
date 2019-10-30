package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User userDetail);

    void deleteUser(Long idx) throws Exception;

    User getUser(Long idx) throws Exception;
}
