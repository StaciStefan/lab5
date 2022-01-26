package com.example.lab5.controllers;

import com.example.lab5.entities.GenreEntity;
import com.example.lab5.entities.MovieEntity;
import com.example.lab5.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.lab5.repositories.MovieRepository;
import com.example.lab5.entities.SerieEntity;
import com.example.lab5.repositories.SerieRepository;

@Controller
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SerieRepository serieRepository;

//    @GetMapping("/genre")
//    public String genre(Model model) {
//        Iterable<GenreEntity> genreEntities = genreRepository.findAll();
//        model.addAttribute("genreEntities",genreEntities);
//        return "genre";
//    }

//    @GetMapping("/genre/add")
//    public String genreAdd(Model model) {
//        return "genre-add";
//    }
//
//    @PostMapping("/genre/add")
//    public String genrePostAdd(@RequestParam String name, @RequestParam String description, Model model) {
//        GenreEntity genreEntity = new GenreEntity(name, description);
//        genreRepository.save(genreEntity);
//        return "redirect:/genre";
//    }

//    @PostMapping("/genre/{id}/remove")
//    public String genrePostRemove(@PathVariable(value = "id") long id, Model model) {
//        GenreEntity post = genreRepository.findById(id).orElseThrow();
//        genreRepository.delete(post);
//        return "redirect:/genre";
//    }

    @GetMapping("/genre")
    public String genreDetails(Model model) {

        Iterable<MovieEntity> movieEntities = movieRepository.findAll();
        model.addAttribute("movieEntities",movieEntities);

        Iterable<SerieEntity> serieEntities = serieRepository.findAll();
        model.addAttribute("serieEntities",serieEntities);

        return "genre-details";
    }

}
