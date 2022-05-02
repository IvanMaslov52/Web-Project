package com.example.webproject.repos;

import com.example.webproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>  {
    User findByUsername(String username);//почему лучше по id? - primary key
}
