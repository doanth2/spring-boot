package com.fixdeecode.exporttoexcelinjavademo.repository;

import com.fixdeecode.exporttoexcelinjavademo.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRecordRepo extends JpaRepository<ExamRecord, Long> {
}
