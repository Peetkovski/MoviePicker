package com.example.moviepicker.repository;

import com.example.moviepicker.entity.MovieDetailsDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Movie Details Test")
class movieDetailsRepositoryTest {

    @Autowired
    private movieDetailsRepository movieDetailsRepository;

    private List<MovieDetailsDTO> movieDetails = new ArrayList<>();
    public List<MovieDetailsDTO> getAllFilms(){

        return movieDetails;
    }

    @Test
    void getMovieDetailsById() throws IOException, InterruptedException {

        String movieId;
        movieId = "550";
        String movieDetails = "https://api.themoviedb.org/3/movie/"+movieId+"?api_key=17a7e43adb001580f381019e4a272790";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(movieDetails))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        List<MovieDetailsDTO> records = new ArrayList<>();
        String beforePath = "https://image.tmdb.org/t/p/w500";

        JSONObject jsonObject = new JSONObject(response.body());
        MovieDetailsDTO MovieDetailsDTO = new MovieDetailsDTO();
        MovieDetailsDTO.setMovieId(jsonObject.getLong("id"));
        MovieDetailsDTO.setMovieName(jsonObject.getString("original_title"));

        MovieDetailsDTO.setLanguage(jsonObject.getString("original_language"));
        MovieDetailsDTO.setMovieRate(String.valueOf(jsonObject.getDouble("vote_average")));
        MovieDetailsDTO.setMovieDescription(jsonObject.getString("overview"));
        MovieDetailsDTO.setMovieReleaseDate(" " + jsonObject.getString("release_date"));

        String afterPath = String.valueOf(jsonObject.get("poster_path"));
        MovieDetailsDTO.setMoviePoster(beforePath + afterPath);

        if (afterPath.equals("null")) {
            MovieDetailsDTO.setMoviePoster("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/2048px-No_image_available.svg.png");
            records.add(MovieDetailsDTO);

        } else{
            records.add(MovieDetailsDTO);
        }
        this.movieDetails = records;
    }


    @Test
    void getMovieDetails(){
    }

}