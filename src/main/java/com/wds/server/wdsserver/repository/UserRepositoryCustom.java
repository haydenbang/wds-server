package com.wds.server.wdsserver.repository;

import com.wds.server.wdsserver.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getAllUsersOrderByCreatedDate();
}
