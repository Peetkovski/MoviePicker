package com.example.moviepicker.controller;

import com.example.moviepicker.entity.MovieDetailsDTO;
import com.example.moviepicker.service.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/movie")
public class GetMovieDetails {

    @Autowired
    private MovieDetails movieDetails;

    @GetMapping("/{movieName}")
    public String getMovieDetails(@PathVariable String movieName, Model model) throws IOException, InterruptedException {

        movieDetails.getMovieByName(movieName);

        List<MovieDetailsDTO> movieDetailsDTOList = movieDetails.getAllFilms();


        model.addAttribute("movies", movieDetailsDTOList );

        return "movie";
    }

}
