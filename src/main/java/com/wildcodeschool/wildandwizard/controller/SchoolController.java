package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SchoolController {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/schools")
    public String getAll(Model model) {
        model.addAttribute("schools", schoolRepository.findAll());
        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<School> optionalSchool = schoolRepository.findById(id);
            if (optionalSchool.isPresent()) {
                model.addAttribute("school", optionalSchool.get());
            } else {

            }
        } else {
            model.addAttribute("school", new School());
        }
        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {
        schoolRepository.save(school);
        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
        schoolRepository.deleteById(id);
        return "redirect:/schools";
    }
}
