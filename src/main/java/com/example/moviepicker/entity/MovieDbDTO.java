package com.example.moviepicker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDbDTO {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long movieId;
    private String movieName;
    private String movieRate;
    private String language;

}
