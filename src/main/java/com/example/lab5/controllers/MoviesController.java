package com.example.lab5.controllers;

import com.example.lab5.entities.MovieEntity;
import com.example.lab5.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MoviesController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
        public String movies(Model model) {
        Iterable<MovieEntity> movieEntities = movieRepository.findAll();
        model.addAttribute("movieEntities",movieEntities);
        return "movie";
    }

    @GetMapping("/movies/add")
    public String moviesAdd(Model model) {
        return "movie-add";
    }

    @PostMapping("/movies/add")
        public String moviesPostAdd(@RequestParam String title, @RequestParam String age, @RequestParam String country, @RequestParam String genre, @RequestParam String description, Model model) {
        MovieEntity movieEntity = new MovieEntity(title, age, country, genre, description);
        movieRepository.save(movieEntity);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{id}")
    public String moviesDetails(@PathVariable(value = "id") long id, Model model) {
        if(!movieRepository.existsById(id)){
            return "redirect:/movies";
        }

        Optional<MovieEntity> posts = movieRepository.findById(id);
        ArrayList<MovieEntity> res = new ArrayList<>();
        posts.ifPresent(res::add);
        model.addAttribute("posts", res);
        return "movie-details";
    }

    @GetMapping("/movies/{id}/edit")
    public String moviesEdit(@PathVariable(value = "id") long id, Model model) {
        if(!movieRepository.existsById(id)){
            return "redirect:/movies";
        }

        Optional<MovieEntity> posts = movieRepository.findById(id);
        ArrayList<MovieEntity> res = new ArrayList<>();
        posts.ifPresent(res::add);
        model.addAttribute("posts", res);
        return "movie-edit";
    }

    @PostMapping("/movies/{id}/edit")
    public String moviesPostEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String age, @RequestParam String country, @RequestParam String genre, @RequestParam String description, Model model) {
        MovieEntity post = movieRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAge(age);
        post.setCountry(country);
        post.setGenre(genre);
        post.setDescription(description);
        movieRepository.save(post);
        return "redirect:/movies";
    }

    @PostMapping("/movies/{id}/remove")
    public String moviesPostRemove(@PathVariable(value = "id") long id, Model model) {
        MovieEntity post = movieRepository.findById(id).orElseThrow();
        movieRepository.delete(post);
        return "redirect:/movies";
    }
}
