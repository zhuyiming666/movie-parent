package com.itheima.service;

import com.itheima.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    void save(Movie movie);

    Movie findById(Integer id);

    void update(Movie movie);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List<Movie> findByCid(Integer cid);
}
