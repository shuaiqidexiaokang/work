package com.lzk.repository;

import com.lzk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<User, Serializable> {
}
