package com.luan.spring.files.excel.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamScheduleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loHP;
    private String maHP;
    private String tenHP;
    private int soTC;
    private int sl;
    private String htThi;
    private String buoi;
    private String gioThi;
    private String ngayThi;
    private String phongThi;
    private String gvChamThi1;
    private String gvChamThi2;
    private String ct1;
    private String ct2;
    private int thu;
    private String lop;
    private String khoa;
}
