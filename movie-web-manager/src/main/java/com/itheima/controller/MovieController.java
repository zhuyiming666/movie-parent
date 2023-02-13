package com.itheima.controller;

import com.itheima.domain.Movie;
import com.itheima.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    //查询影视列表
    @RequestMapping("/admin/movie/findAll")
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    //新增
    @RequestMapping("/admin/movie/save")
    public void save(@RequestBody Movie movie){
        if(movie.getId()==null){     //id为null代表新增
            movieService.save(movie);
        }else {                      //id不为null代表修改
            movieService.update(movie);
        }

    }

    //根据影视id查询影视信息
    @RequestMapping("/admin/movie/findById")
    public Movie findById(Integer id){
        return movieService.findById(id);
    }

    //根据id进行删除
    @RequestMapping("/admin/movie/deleteById")
    public void deleteById(Integer id){
        movieService.deleteById(id);
    }

    //根据id数组进行删除
    @RequestMapping("/admin/movie/deleteByIds")
    public void deleteByIds(Integer[] ids){
        movieService.deleteByIds(ids);
    }
}
