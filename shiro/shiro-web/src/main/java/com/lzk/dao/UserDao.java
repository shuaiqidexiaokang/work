package com.lzk.dao;

import com.lzk.vo.User;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String userName);

    List<String> getRolesByUserName(String userName);
}
