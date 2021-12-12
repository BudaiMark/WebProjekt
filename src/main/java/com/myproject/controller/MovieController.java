package com.myproject.controller;


import com.myproject.model.Movie;
import com.myproject.service.MovieException;
import com.myproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieController {
    @Autowired private MovieService service;

    @GetMapping("/movies")
    public String getMovieList(Model model){
        List<Movie> movieList = service.listAll();
        model.addAttribute("movieList", movieList);

        return "movies";
    }

    @GetMapping("/movies/new")
    public String showAddPage(Model model){
        model.addAttribute("movie", new Movie());
        model.addAttribute("pageTitle", "Add New Movie");
        return "movie_page";
    }

    @PostMapping("/movies/save")
    public String saveMovie(Movie movie, RedirectAttributes ra){
        service.save(movie);
        ra.addFlashAttribute("message", "The movie has been saved successfully");
        return "redirect:/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Movie movie = service.get(id);
            model.addAttribute("movie", movie);
            model.addAttribute("pageTitle", "Edit Existing Movie (ID: " + id + ")");
            return "movie_page";
        } catch (MovieException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/movies";
        }
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The movie with ID " + id + " has been deleted successfully.");
        } catch (MovieException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/movies";
    }

}
