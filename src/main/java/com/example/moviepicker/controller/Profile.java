package com.example.moviepicker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Profile {


    @GetMapping("/profile/{profileName}")
    public String goToProfile(@PathVariable String profileName, Model model){

        return "profile";
    }

}
