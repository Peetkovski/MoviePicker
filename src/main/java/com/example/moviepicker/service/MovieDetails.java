package com.example.moviepicker.service;

import com.example.moviepicker.entity.MovieDetailsDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MovieDetails {

    Logger logger = Logger.getLogger("com.service.GetMovieDetails");


    private List<MovieDetailsDTO> movieDetails = new ArrayList<>();
    public List<MovieDetailsDTO> getAllFilms(){

        logger.info("Returning Movie Details");
        return movieDetails;
    }

    public void getMovieByName(String movieName) throws IOException, InterruptedException {
        String movieDetails = "https://api.themoviedb.org/3/movie/"+movieName+"?api_key=17a7e43adb001580f381019e4a272790";
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
            int year = Calendar.getInstance().get(Calendar.YEAR);

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
}
