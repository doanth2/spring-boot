package com.fixdeecode.exporttoexcelinjavademo.service;

import com.fixdeecode.exporttoexcelinjavademo.entity.ExamRecord;

import java.util.List;
import java.util.Optional;

public interface ExamRecordService {
    List<ExamRecord>  findExcelAll();
   void createExam(ExamRecord examRecord) throws Exception;
}
