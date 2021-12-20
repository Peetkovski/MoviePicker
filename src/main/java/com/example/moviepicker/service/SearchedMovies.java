package com.example.moviepicker.service;


import com.example.moviepicker.entity.MovieDbDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchedMovies {

    private List<MovieDbDTO> allFilms = new ArrayList<MovieDbDTO>();




    public List<MovieDbDTO> getAllFilms(){

        return allFilms;
    }


    @PostConstruct
    void getMovieByName() throws IOException, InterruptedException {
        String mostPopularMoviesJson = "https://api.themoviedb.org/3/search/movie?api_key=17a7e43adb001580f381019e4a272790&query=Gump";
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


            records.add(movieDbDTO);
        }
        this.allFilms = records;


    }
}

//   <td th:text="${p.getMovieName()}"></td>
//        <td th:text="${p.getMovieRate()}"></td>