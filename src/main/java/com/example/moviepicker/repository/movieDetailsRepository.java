package com.example.moviepicker.repository;

import com.example.moviepicker.entity.MovieDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface movieDetailsRepository extends JpaRepository<MovieDetailsDTO,Long> {
}
