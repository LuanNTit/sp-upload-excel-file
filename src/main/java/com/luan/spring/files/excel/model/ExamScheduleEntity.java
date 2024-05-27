package com.luan.spring.files.excel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam-schedule")
public class ExamScheduleEntity {
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
