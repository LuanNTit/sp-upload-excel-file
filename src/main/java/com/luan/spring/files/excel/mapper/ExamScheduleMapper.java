package com.luan.spring.files.excel.mapper;

import com.luan.spring.files.excel.dto.ExamScheduleDTO;
import com.luan.spring.files.excel.model.ExamScheduleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamScheduleMapper {
    ExamScheduleDTO mapToExamScheduleDto(ExamScheduleEntity examScheduleEntity);
    ExamScheduleEntity mapToExamScheduleEntity(ExamScheduleDTO examScheduleDTO);
    List<ExamScheduleEntity> mapToExamScheduleEntities(List<ExamScheduleDTO> examScheduleDTOS);
    List<ExamScheduleDTO> mapToExamScheduleDtos(List<ExamScheduleEntity> examScheduleEntities);
}
