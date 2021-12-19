package com.example.moviepicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class MoviePickerApplication {
   static String mostPopularMoviesJson = "https://api.themoviedb.org/3/movie/popular?api_key=17a7e43adb001580f381019e4a272790";

    public static void main(String[] args) throws IOException, InterruptedException {

        SpringApplication.run(MoviePickerApplication.class, args);




    }



}
