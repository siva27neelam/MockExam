package com.mockexam.MockExam.service;

import com.mockexam.MockExam.entities.Question;
import com.mockexam.MockExam.entities.QuestionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QuestionImportService {

    @Autowired
    private QuestionRepository questionRepository;

    public void importQuestions(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        List<Question> questions = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Question question = new Question();

            question.setQuestionText(row.getCell(0).getStringCellValue());
            question.setOptions(List.of(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue()
            ));
            question.setCorrectAnswer(row.getCell(5).getStringCellValue());

            questions.add(question);
        }

        questionRepository.saveAll(questions);
    }
}

