package com.itheima.controller;

import com.itheima.common.VodTemplate;
import com.itheima.domain.Movie;
import com.itheima.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private VodTemplate vodTemplate;

    //根据栏目cid查询所有影视对象
    @RequestMapping("/portal/movie/findByCid")
    public List<Movie> findAll(Integer cid){
        return movieService.findByCid(cid);
    }

    //根据影视id查询影视详情
    @RequestMapping("/portal/movie/findById")
    public Movie findById(Integer id) throws Exception {
        Movie movie = movieService.findById(id);
        //根据playId去阿里云换取auth
        String playAuth=vodTemplate.getVideoPlayAuth(movie.getPlayId()).getPlayAuth();
        movie.setPlayAuth(playAuth);
        return movie;
    }

}
