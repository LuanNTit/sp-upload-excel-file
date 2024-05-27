package com.luan.spring.files.excel.repository;

import com.luan.spring.files.excel.model.ExamScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamCheduleRepository extends JpaRepository<ExamScheduleEntity, Long> {

}
