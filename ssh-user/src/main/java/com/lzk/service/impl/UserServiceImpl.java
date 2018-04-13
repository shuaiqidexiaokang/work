package com.lzk.service.impl;

import com.lzk.entity.User;
import com.lzk.repository.UserRepository;
import com.lzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Integer save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.saveOrUpdate(user);
    }

    @Override
    public User get(Integer id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
