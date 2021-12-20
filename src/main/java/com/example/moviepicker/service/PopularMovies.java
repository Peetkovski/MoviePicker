package com.example.moviepicker.service;

import com.example.moviepicker.entity.MovieDbDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopularMovies {
    private List<MovieDbDTO> allPopularFilms = new ArrayList<MovieDbDTO>();

    public List<MovieDbDTO>getAllPopularFilms(){

        return allPopularFilms;
    }

    @PostConstruct
    public void getMostPopularMovies() throws IOException, InterruptedException {
        String mostPopularMoviesJson = "https://api.themoviedb.org/3/movie/popular?api_key=17a7e43adb001580f381019e4a272790";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(mostPopularMoviesJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        List<MovieDbDTO> records = new ArrayList<>();
        String beforePath = "https://image.tmdb.org/t/p/w500";

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for(int i = 0;i <jsonArray.length();i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            MovieDbDTO movieDbDTO = new MovieDbDTO();
            movieDbDTO.setMovieId(jsonObject1.getLong("id"));
            movieDbDTO.setMovieName(jsonObject1.getString("title"));
            movieDbDTO.setMovieRate(String.valueOf(jsonObject1.getDouble("vote_average")));
            String afterPath = String.valueOf(jsonObject1.get("poster_path"));
            movieDbDTO.setMoviePoster(beforePath+afterPath);
            movieDbDTO.setMoviePosition(String.valueOf(i+1)+") ");



            records.add(movieDbDTO);
        }
        this.allPopularFilms = records;


    }

}
