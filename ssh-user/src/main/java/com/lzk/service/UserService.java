package com.lzk.service;

import com.lzk.entity.User;

import java.util.List;

public interface UserService {
    Integer save(User user);
    void delete(Integer id);
    void saveOrUpdate(User user);
    User get(Integer id);
    List<User> findAll();
}
