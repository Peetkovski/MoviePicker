package com.example.moviepicker.controller;

import com.example.moviepicker.entity.MovieDbDTO;
import com.example.moviepicker.service.PopularMovies;
import com.example.moviepicker.service.SearchedMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {

        @Autowired
        private SearchedMovies searchedMovies;

        @Autowired
        private PopularMovies popularMovies;

        @GetMapping("/popular")
        public String goToHome(Model model){

               List<MovieDbDTO> movieSelected = searchedMovies.getAllFilms();
               List<MovieDbDTO> popularMoviesSelected = popularMovies.getAllPopularFilms();

               model.addAttribute("movies", popularMoviesSelected);


            return "index";
        }
}
