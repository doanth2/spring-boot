package com.fixdeecode.exporttoexcelinjavademo.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import com.fixdeecode.exporttoexcelinjavademo.entity.ExamRecord;
import com.fixdeecode.exporttoexcelinjavademo.service.ExamRecordService;
import com.fixdeecode.exporttoexcelinjavademo.until.ExcelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExamRecordController {

    @Autowired
    private ExamRecordService examRecordService;

    @GetMapping("/export/excel")
    public void exportIntoExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ExamRecord> listOfRecords = examRecordService.findExcelAll();

        ExcelGenerator generator = new ExcelGenerator(listOfRecords);

        generator.generate(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExamRecord(@RequestBody ExamRecord examRecord) {
        try {
            examRecordService.createExam(examRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body("Exam record created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create exam record: " + e.getMessage());
        }
    }

}