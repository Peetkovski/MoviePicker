package com.example.moviepicker.controller;

import com.example.moviepicker.entity.MovieDbDTO;
import com.example.moviepicker.service.MovieDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {


        @Autowired
        private MovieDb movieDb;

        @GetMapping("/home")
        public String goToHome(Model model){

               List<MovieDbDTO> movieDbDTOS = movieDb.getAllFilms();

               model.addAttribute("movies", movieDbDTOS);


            return "index";
        }
}
