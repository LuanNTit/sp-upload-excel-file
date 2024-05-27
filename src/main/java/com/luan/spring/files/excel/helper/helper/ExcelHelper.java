package com.luan.spring.files.excel.helper.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.luan.spring.files.excel.dto.ExamScheduleDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"TT", "Lớp HP", "Mã HP", "Tên HP", "Số TC", "SL", "HT Thi", "Buổi", "Giờ thi", "Ngày thi", "Phòng thi", "GV chấm thi 1", "GV chấm thi 2", "CT1", "CT2", "Thứ", "Lớp", "Khóa"};
    static String SHEET = "KH THI";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream examScheduleDTOToExcel(List<ExamScheduleDTO> exams) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ExamScheduleDTO exam : exams) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(exam.getId());
                row.createCell(1).setCellValue(exam.getLoHP());
                row.createCell(2).setCellValue(exam.getMaHP());
                row.createCell(3).setCellValue(exam.getTenHP());
                row.createCell(4).setCellValue(exam.getSoTC());
                row.createCell(5).setCellValue(exam.getSl());
                row.createCell(6).setCellValue(exam.getHtThi());
                row.createCell(7).setCellValue(exam.getBuoi());
                row.createCell(8).setCellValue(exam.getGioThi());
                row.createCell(9).setCellValue(exam.getNgayThi());
                row.createCell(10).setCellValue(exam.getPhongThi());
                row.createCell(11).setCellValue(exam.getGvChamThi1());
                row.createCell(12).setCellValue(exam.getGvChamThi2());
                row.createCell(13).setCellValue(exam.getCt1());
                row.createCell(14).setCellValue(exam.getCt2());
                row.createCell(15).setCellValue(exam.getThu());
                row.createCell(16).setCellValue(exam.getLop());
                row.createCell(17).setCellValue(exam.getKhoa());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<ExamScheduleDTO> excelToExamScheduleDTO(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<ExamScheduleDTO> examScheduleDTOS = new ArrayList<ExamScheduleDTO>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber <= 5) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                ExamScheduleDTO examDTO = new ExamScheduleDTO();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            examDTO.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            examDTO.setLoHP(currentCell.getStringCellValue());
                            break;
                        case 2:
                            examDTO.setMaHP(currentCell.getStringCellValue());
                            break;
                        case 3:
                            examDTO.setTenHP(currentCell.getStringCellValue());
                            break;
                        case 4:
                            examDTO.setSoTC((int)currentCell.getNumericCellValue());
                            break;
                        case 5:
                            examDTO.setSl((int)currentCell.getNumericCellValue());
                            break;
                        case 6:
                            examDTO.setHtThi(currentCell.getStringCellValue());
                            break;
                        case 7:
                            examDTO.setBuoi(currentCell.getStringCellValue());
                            break;
                        case 8:
                            examDTO.setGioThi(currentCell.getStringCellValue());
                            break;
                        case 9:
//                            examDTO.setNgayThi(currentCell.getStringCellValue());
                            break;
                        case 10:
                            examDTO.setPhongThi(currentCell.getStringCellValue());
                            break;
                        case 11:
                            examDTO.setGvChamThi1(currentCell.getStringCellValue());
                            break;
                        case 12:
                            examDTO.setGvChamThi2(currentCell.getStringCellValue());
                            break;
                        case 13:
                            examDTO.setCt1(currentCell.getStringCellValue());
                            break;
                        case 14:
                            examDTO.setCt2(currentCell.getStringCellValue());
                            break;
                        case 15:
                            examDTO.setThu((int)currentCell.getNumericCellValue());
                            break;
                        case 16:
                            examDTO.setLop(currentCell.getStringCellValue());
                            break;
                        case 17:
                            examDTO.setKhoa(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                examScheduleDTOS.add(examDTO);
            }

            workbook.close();

            return examScheduleDTOS;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
