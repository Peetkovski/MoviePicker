package com.example.moviepicker.controller;

import com.example.moviepicker.entity.MovieDbDTO;
import com.example.moviepicker.service.PopularMovies;
import com.example.moviepicker.service.SearchedMovies;
import com.example.moviepicker.service.SortedByRateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class Home {

        @Autowired
        private SortedByRateMovies sortedByRateMovies;

        @Autowired
        private SearchedMovies searchedMovies;

        @Autowired
        private PopularMovies popularMovies;

        @GetMapping("/popular")
        public String goToMostPopular(Model model){

               List<MovieDbDTO> popularMoviesSelected = popularMovies.getAllPopularFilms();

               model.addAttribute("movies", popularMoviesSelected);


            return "index";
        }

    @PostMapping("/search")
    public String searchMovie(@ModelAttribute MovieDbDTO movieDbDTO, String movieName, Model model ) throws IOException, InterruptedException {
        searchedMovies.getMovieByName(movieName);



        model.addAttribute("movies", movieDbDTO);

        return "search";
    }

        @GetMapping("/search/{movieName}")
    public String goToSearch(@PathVariable("movieName") String movieName, Model model ) throws IOException, InterruptedException {
            searchedMovies.getMovieByName(movieName);

            List<MovieDbDTO> movieSelected = searchedMovies.getAllFilms();


            model.addAttribute("movies", movieSelected);

            return "search";
        }
    @GetMapping("/search")
    public String goToSearch(Model model ) throws IOException, InterruptedException {



        return "search";
    }


}
