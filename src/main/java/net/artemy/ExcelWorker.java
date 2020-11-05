package net.artemy;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelWorker {
    private static HashMap<String, Integer> subjectPositions =  new HashMap<String, Integer>();
    public ExcelWorker() {
    }

    public String parseExcel(String filePath, SchoolClass schoolClass) {
        String errorText = "";
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            int counter = 0;
            for (Row cells : sheet) {
                for (Cell cell : cells) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        schoolClass.createNewStudent(sheet, cell.getRowIndex());
                        break;
                    }
                    if (cell == null || cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
                        break;
                    }
                    if (cell.getStringCellValue().startsWith("№ п/п")) {
                        if (counter == 0) {
                          //  subjectPositions = new HashMap<String, Integer>();
                            initSubjectPositions(sheet, cell.getRowIndex());
                            counter += 1;
                        }
                        break;
                    }
                    if (cell.getStringCellValue().startsWith("Классный руководитель")) {
                        schoolClass.setTeacher(cell.getStringCellValue());
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            errorText = e.getMessage();
        } catch (IOException e) {
            errorText = e.getMessage();
        }
        return errorText;
    }
    private void initSubjectPositions(HSSFSheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        short lastNum = row.getLastCellNum();
        for (int i = 2; i <= lastNum; i++) {
             if (row.getCell(i) == null || row.getCell(i).getStringCellValue() == null || row.getCell(i).getStringCellValue().equals("") || row.getCell(i).getStringCellValue().equals("Фамилия, имя, отчество")) {
                 continue;
             }
             subjectPositions.put(row.getCell(i).getStringCellValue(), i);
        }
    }

    public static HashMap<String, Integer> getSubjectPositions() {
        return subjectPositions;
    }
}
