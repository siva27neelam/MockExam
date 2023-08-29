package com.mockexam.MockExam.controller;

import com.mockexam.MockExam.entities.Question;
import com.mockexam.MockExam.entities.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller

public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    private int currentQuestionIndex = 0;
    private List<Question> questions;

    @PostConstruct
    public void saveQuestions() {
        Question question = new Question();
        question.setQuestionText("hello");
        question.setId(1L);
        question.setOptions(Arrays.asList("a", "b"));
        question.setCorrectAnswer("a");
        questionRepository.save(question);
    }


    @GetMapping("/questions")
    public String showQuestion(Model model) {
        if (questions == null) {
            questions = questionRepository.findAll();
        }

        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            model.addAttribute("question", question);
            model.addAttribute("currentIndex", currentQuestionIndex + 1);
            currentQuestionIndex++;
            return "question";
        } else {
            // Reset the quiz after completion
            currentQuestionIndex = 0;
            questions = null;
            return "quiz-complete";
        }
    }

    @PostMapping("/questions")
    public String submitAnswer(@RequestParam String selectedOption, Model model) {
        Question question = questions.get(currentQuestionIndex - 1); // Current index points to next question
        if (selectedOption.equals(question.getCorrectAnswer())) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Incorrect. The correct answer is: " + question.getCorrectAnswer());
        }
        return "answer-feedback";
    }
}




