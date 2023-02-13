package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.domain.Movie;
import com.itheima.mapper.MovieMapper;
import com.itheima.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> findAll() {
        return movieMapper.selectList(null);
    }

    @Override
    public void save(Movie movie) {
        movieMapper.insert(movie);
    }

    @Override
    public Movie findById(Integer id) {
        return movieMapper.selectById(id);
    }

    @Override
    public void update(Movie movie) {
        movieMapper.updateById(movie);
    }

    @Override
    public void deleteById(Integer id) {
        movieMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
             movieMapper.deleteById(id);
        }
    }

    @Override
    public List<Movie> findByCid(Integer cid) {
        LambdaQueryWrapper<Movie> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Movie::getCid,cid);
        return movieMapper.selectList(wrapper);
    }
}
