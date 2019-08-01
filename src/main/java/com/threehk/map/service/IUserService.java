package com.threehk.map.service;

import com.threehk.map.model.User;

import java.util.List;

public interface IUserService {
    User createUser(String username, String password, List<Long> roleId);
    boolean removeUser(Long id);
    List<User> getAllUser();
}
