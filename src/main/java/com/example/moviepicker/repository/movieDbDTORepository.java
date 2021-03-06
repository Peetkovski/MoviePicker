package com.example.moviepicker.repository;

import com.example.moviepicker.entity.MovieDbDTO;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface movieDbDTORepository extends JpaRepository<MovieDbDTO, Long> {

}
