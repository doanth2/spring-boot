package com.fixdeecode.exporttoexcelinjavademo.service;

import com.fixdeecode.exporttoexcelinjavademo.entity.ExamRecord;
import com.fixdeecode.exporttoexcelinjavademo.repository.ExamRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamRecordServiceImpl implements ExamRecordService{
    @Autowired
    private ExamRecordRepo examRecordRepo;
    @Override
    public List<ExamRecord> findExcelAll() {
        return examRecordRepo.findAll();
    }

    @Override
    public void createExam(ExamRecord examRecord) throws Exception {
         examRecordRepo.save(examRecord);
    }

}
