package com.example.lab5.controllers;
import com.example.lab5.entities.SerieEntity;
import com.example.lab5.repositories.SerieRepository;
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
public class SeriesController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public String movies(Model model) {
        Iterable<SerieEntity> serieEntities = serieRepository.findAll();
        model.addAttribute("serieEntities",serieEntities);
        return "series";
    }

    @GetMapping("/series/add")
    public String seriesAdd(Model model) {
        return "series-add";
    }

    @PostMapping("/series/add")
    public String seriesPostAdd(@RequestParam String title, @RequestParam String age, @RequestParam String country, @RequestParam String episodes, @RequestParam String genre, @RequestParam String description, Model model) {
        SerieEntity seriesEntity = new SerieEntity(title, age, country, episodes, genre, description);
        serieRepository.save(seriesEntity);
        return "redirect:/series";
    }

    @GetMapping("/series/{id}")
    public String seriesDetails(@PathVariable(value = "id") long id, Model model) {
        if(!serieRepository.existsById(id)){
            return "redirect:/series";
        }

        Optional<SerieEntity> posts = serieRepository.findById(id);
        ArrayList<SerieEntity> res = new ArrayList<>();
        posts.ifPresent(res::add);
        model.addAttribute("posts", res);
        return "series-details";
    }

    @GetMapping("/series/{id}/edit")
    public String seriesEdit(@PathVariable(value = "id") long id, Model model) {
        if(!serieRepository.existsById(id)){
            return "redirect:/movies";
        }

        Optional<SerieEntity> posts = serieRepository.findById(id);
        ArrayList<SerieEntity> res = new ArrayList<>();
        posts.ifPresent(res::add);
        model.addAttribute("posts", res);
        return "series-edit";
    }

    @PostMapping("/series/{id}/edit")
    public String moviesPostEdit(@PathVariable(value = "id") long id, @RequestParam String episodes, @RequestParam String age, @RequestParam String country, @RequestParam String title, @RequestParam String genre, @RequestParam String description, Model model) {
        SerieEntity post = serieRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAge(age);
        post.setCountry(country);
        post.setEpisodes(episodes);
        post.setGenre(genre);
        post.setDescription(description);
        serieRepository.save(post);
        return "redirect:/series";
    }

    @PostMapping("/series/{id}/remove")
    public String moviesPostRemove(@PathVariable(value = "id") long id, Model model) {
        SerieEntity post = serieRepository.findById(id).orElseThrow();
        serieRepository.delete(post);
        return "redirect:/series";
    }
}
