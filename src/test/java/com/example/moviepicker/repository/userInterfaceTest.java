package com.example.moviepicker.repository;

import com.example.moviepicker.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@SpringBootTest
@DisplayName("User Test")
class userInterfaceTest {
    Logger logger = Logger.getLogger("com.service.UserTest");

    @Autowired
    private userInterface userInterface;

    @Test
    @DisplayName("Adding Multiple Users To Database")
    void addMultipleUsers(){
        List<User> user = new ArrayList<>();
                 user.add(new User(null,"Tom", "Chuj123", "TOmasz124"));
                 user.add(new User(null,"Piotr", "Peetkovski", "Peet12345"));
        for(User user1 : user) {
            userInterface.save(user1);
        }
        logger.info("Adding Multiple Users");

    }

    @Test
    @DisplayName("Adding single user to database")
    void addUser(){
        User user =
        new User(null,"Singe", "User12345", "Passsw123");
        userInterface.save(user);
        logger.info("Adding Multiple Users");

    }

    @Test
    @DisplayName("Display all users")
    void getUsers(){
       List<User> users =userInterface.findAll();

        System.out.println(users);
        logger.info("List of Users");

    }

    @Test
    @DisplayName("Display user By Id")
    void getUserById(){
        List<User> user = Collections.singletonList(userInterface.findUserByUserId(1L));
        System.out.println(user);
        logger.info("Finding user by ID ");

    }

    @Test
    @DisplayName("Get User By NickName")
    void getUserByNickName(){
        User user = userInterface.findUserBynickName("Chuj123");
        System.out.println(user);
        logger.info("Finding user by Nick Name ");

    }

}