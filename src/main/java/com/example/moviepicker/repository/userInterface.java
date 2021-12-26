package com.example.moviepicker.repository;

import com.example.moviepicker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userInterface extends JpaRepository<User,Long> {

    User findUserByUserId(Long id);

    User findUserBynickName(String name);
}
