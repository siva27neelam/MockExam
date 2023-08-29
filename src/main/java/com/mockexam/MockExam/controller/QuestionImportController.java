package com.mockexam.MockExam.controller;

import com.mockexam.MockExam.service.QuestionImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/import")
public class QuestionImportController {

    @Autowired
    private QuestionImportService questionImportService;

    @GetMapping
    public String showImportForm() {
        return "import-form";
    }

    @PostMapping
    public String importQuestions(@RequestParam("file") MultipartFile file, Model model) {
        try {
            questionImportService.importQuestions(file);
            model.addAttribute("message", "Questions imported successfully!");
        } catch (IOException e) {
            model.addAttribute("error", "Error importing questions.");
        }
        return "import-form";
    }
}
