package com.luan.spring.files.excel.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.luan.spring.files.excel.dto.ExamScheduleDTO;
import com.luan.spring.files.excel.mapper.ExamScheduleMapper;
import com.luan.spring.files.excel.model.ExamScheduleEntity;
import com.luan.spring.files.excel.repository.ExamCheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luan.spring.files.excel.helper.helper.ExcelHelper;

@Service
public class ExcelService {
  @Autowired
  ExamCheduleRepository examCheduleRepository;
  @Autowired
  ExamScheduleMapper examScheduleMapper;

  public void save(MultipartFile file) {
    try {
      List<ExamScheduleDTO> examScheduleDTOS = ExcelHelper.excelToExamScheduleDTO(file.getInputStream());
      List<ExamScheduleEntity> examScheduleEntities = examScheduleMapper.mapToExamScheduleEntities(examScheduleDTOS);
      examCheduleRepository.saveAll(examScheduleEntities);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<ExamScheduleEntity> examScheduleEntities = examCheduleRepository.findAll();

    ByteArrayInputStream in = ExcelHelper.examScheduleDTOToExcel(examScheduleMapper.mapToExamScheduleDtos(examScheduleEntities));
    return in;
  }

  public List<ExamScheduleDTO> getAllExamScheduleDTOS() {

    return examScheduleMapper.mapToExamScheduleDtos(examCheduleRepository.findAll());
  }
}
