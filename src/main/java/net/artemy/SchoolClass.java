package net.artemy;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.Map;

public class SchoolClass {
    private ArrayList<Student> students;
    private String teacher;
    private static ArrayList<Student> excellentStudents1stQ;
    private static ArrayList<Student> excellentStudents2ndQ;
    private static ArrayList<Student> excellentStudents3rdQ;
    private static ArrayList<Student> excellentStudents4thQ;
    private static ArrayList<Student> excellentStudentsYear;
    private static ArrayList<Student> almostExcellentStudents1stQ;
    private static ArrayList<Student> almostExcellentStudents2ndQ;
    private static ArrayList<Student> almostExcellentStudents3rdQ;
    private static ArrayList<Student> almostExcellentStudents4thQ;
    private static ArrayList<Student> almostExcellentStudentsYear;
    private static ArrayList<Student> wellDoneStudents1stQ;
    private static ArrayList<Student> wellDoneStudents2ndQ;
    private static ArrayList<Student> wellDoneStudents3rdQ;
    private static ArrayList<Student> wellDoneStudents4thQ;
    private static ArrayList<Student> wellDoneStudentsYear;
    private static ArrayList<Student> notBadStudents1stQ;
    private static ArrayList<Student> notBadStudents2ndQ;
    private static ArrayList<Student> notBadStudents3rdQ;
    private static ArrayList<Student> notBadStudents4thQ;
    private static ArrayList<Student> notBadStudentsYear;
    private static StringBuilder result1stQuarter;
    private static StringBuilder result2ndQuarter;
    private static StringBuilder result3rdQuarter;
    private static StringBuilder result4thQuarter;
    private static StringBuilder resultYear;

    public SchoolClass() {
        students = new ArrayList<Student>();
        initAll();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    private void initAll() {
        result1stQuarter = new StringBuilder();
        result2ndQuarter = new StringBuilder();
        result3rdQuarter = new StringBuilder();
        result4thQuarter = new StringBuilder();
        resultYear = new StringBuilder();
        excellentStudents1stQ = new ArrayList<Student>();
        excellentStudents2ndQ = new ArrayList<Student>();
        excellentStudents3rdQ = new ArrayList<Student>();
        excellentStudents4thQ = new ArrayList<Student>();
        excellentStudentsYear = new ArrayList<Student>();
        almostExcellentStudents1stQ = new ArrayList<Student>();
        almostExcellentStudents2ndQ = new ArrayList<Student>();
        almostExcellentStudents3rdQ = new ArrayList<Student>();
        almostExcellentStudents4thQ = new ArrayList<Student>();
        almostExcellentStudentsYear = new ArrayList<Student>();
        wellDoneStudents1stQ = new ArrayList<Student>();
        wellDoneStudents2ndQ = new ArrayList<Student>();
        wellDoneStudents3rdQ = new ArrayList<Student>();
        wellDoneStudents4thQ = new ArrayList<Student>();
        wellDoneStudentsYear = new ArrayList<Student>();
        notBadStudents1stQ = new ArrayList<Student>();
        notBadStudents2ndQ = new ArrayList<Student>();
        notBadStudents3rdQ = new ArrayList<Student>();
        notBadStudents4thQ = new ArrayList<Student>();
        notBadStudentsYear = new ArrayList<Student>();
    }

    public void countStatistics() {
        countExcellentPupils(students);
        countAlmostExcellentPupils(students);
        countWellDonePupils(students);
        countNotBadPupils(students);
    }

    public void createNewStudent(HSSFSheet sheet, int rowIndex) {
        Student student = new Student();
        Row row = sheet.getRow(rowIndex);
        student.setStudentName(row.getCell(1).getStringCellValue());
        Map<String, Integer> subjectPositions = ExcelWorker.getSubjectPositions();
        Period period1Q = getQuorterResults(sheet, rowIndex, subjectPositions);
        Period period2Q = getQuorterResults(sheet, rowIndex + 1, subjectPositions);
        Period period3Q = getQuorterResults(sheet, rowIndex + 2, subjectPositions);
        Period period4Q = getQuorterResults(sheet, rowIndex + 3, subjectPositions);
        Period periodYear = getQuorterResults(sheet, rowIndex + 7, subjectPositions);

        student.setFirstQuarter(period1Q);
        student.setSecondQuarter(period2Q);
        student.setThirdQuarter(period3Q);
        student.setFourthQuarter(period4Q);
        student.setYear(periodYear);
        students.add(student);
    }

    private Period getQuorterResults(HSSFSheet sheet, int rowIndex, Map<String, Integer> subjectPositions) {
        Period p = new Period();
        Row row = sheet.getRow(rowIndex);
        for (Map.Entry<String, Integer> entry : subjectPositions.entrySet()) {
            Integer mark = getIntValue(row.getCell(entry.getValue()));
            if (mark != null) {
                p.addMark(entry.getKey(), mark);
            }
        }
        return p;
    }

    private void getExcellentStudents(ArrayList<Student> excellentStudents, Period p, StringBuilder result, Student student) {
        boolean isExcellentPupil = true;
        Map<String, Integer> marks = p.getMarks();
        boolean hasAtLeastOneMark = false;
        for (Map.Entry<String, Integer> stringIntegerEntry : marks.entrySet()) {
            hasAtLeastOneMark = true;
            if (stringIntegerEntry.getValue() != 5) {
                isExcellentPupil = false;
                break;
            }
        }
        if (hasAtLeastOneMark && isExcellentPupil) {
            excellentStudents.add(student);
            result.append("Отличник: " + student.getStudentName() + "\n");
        }
    }

    private void countExcellentPupils(ArrayList<Student> students) {
        for (Student student : students) {
            getExcellentStudents(excellentStudents1stQ, student.getFirstQuarter(), result1stQuarter, student);
            getExcellentStudents(excellentStudents2ndQ, student.getSecondQuarter(), result2ndQuarter, student);
            getExcellentStudents(excellentStudents3rdQ, student.getThirdQuarter(), result3rdQuarter, student);
            getExcellentStudents(excellentStudents4thQ, student.getFourthQuarter(), result4thQuarter, student);
            getExcellentStudents(excellentStudentsYear, student.getYear(), resultYear, student);
        }
    }

    private void getAlmostExcellentPupil(ArrayList<Student> almostExcellentStudents, Period p, StringBuilder result, Student student) {
        boolean hasAtLeastOneMark = false;
        int markBcounter = 0;
        boolean hasCandLessMarks = false;
        Map<String, Integer> marks = p.getMarks();
        String subject = "";
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            if (entry.getValue() == 4) {
                markBcounter += 1;
                hasAtLeastOneMark = true;
                subject = entry.getKey();
            }
            if (entry.getValue() < 4) {
                hasAtLeastOneMark = true;
            }
        }
        if (hasAtLeastOneMark && markBcounter == 1 && !hasCandLessMarks) {
            almostExcellentStudents.add(student);
            result.append("Почти отличник: " + student.getStudentName() + ", предмет - " + subject + "\n");
        }
    }

    private void countAlmostExcellentPupils(ArrayList<Student> students) {
        for (Student student : students) {
            getAlmostExcellentPupil(almostExcellentStudents1stQ, student.getFirstQuarter(), result1stQuarter, student);
            getAlmostExcellentPupil(almostExcellentStudents2ndQ, student.getSecondQuarter(), result2ndQuarter, student);
            getAlmostExcellentPupil(almostExcellentStudents3rdQ, student.getThirdQuarter(), result3rdQuarter, student);
            getAlmostExcellentPupil(almostExcellentStudents4thQ, student.getFourthQuarter(), result4thQuarter, student);
            getAlmostExcellentPupil(almostExcellentStudentsYear, student.getYear(), resultYear, student);
        }
    }

    private void getWellDonePupil(ArrayList<Student> wellDoneStudents, ArrayList<Student> almostExcellentStudents, ArrayList<Student> excellentStudents, Period p, StringBuilder result, Student student) {
        if (excellentStudents.contains(student) || almostExcellentStudents.contains(student)){
            return;
        }
        boolean isWellDonePupil = true;
        boolean hasAtLeastOneMark = false;
        Map<String, Integer> marks = p.getMarks();
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            hasAtLeastOneMark = true;
            if (entry.getValue() < 4){
                isWellDonePupil = false;
            }
        }
        if (hasAtLeastOneMark && isWellDonePupil) {
            wellDoneStudents.add(student);
            result.append("Хорошист: " + student.getStudentName() + "\n");
        }
    }

    private void countWellDonePupils(ArrayList<Student> students) {
        for (Student student : students) {
            getWellDonePupil(wellDoneStudents1stQ, almostExcellentStudents1stQ, excellentStudents1stQ, student.getFirstQuarter(), result1stQuarter, student);
            getWellDonePupil(wellDoneStudents2ndQ, almostExcellentStudents2ndQ, excellentStudents2ndQ, student.getSecondQuarter(), result2ndQuarter, student);
            getWellDonePupil(wellDoneStudents3rdQ, almostExcellentStudents3rdQ, excellentStudents3rdQ, student.getThirdQuarter(), result3rdQuarter, student);
            getWellDonePupil(wellDoneStudents4thQ, almostExcellentStudents4thQ, excellentStudents4thQ, student.getFourthQuarter(), result4thQuarter, student);
            getWellDonePupil(wellDoneStudentsYear, almostExcellentStudentsYear, excellentStudentsYear, student.getYear(), resultYear, student);
        }
    }

    private void getNotBadPupil(ArrayList<Student> notBadStudents, ArrayList<Student> excellentStudents, ArrayList<Student> wellDoneStudents, Period p, StringBuilder result, Student student){
        if (excellentStudents.contains(student) || wellDoneStudents.contains(student)) {
            return;
        }
        boolean hasAtLeastOneMark = false;
        Map<String, Integer> marks = p.getMarks();
        int markCcounter = 0;
        String subject = "";
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            hasAtLeastOneMark = true;
            if (entry.getValue() == 3){
                markCcounter += 1;
                subject = entry.getKey();
            }
        }
        if (hasAtLeastOneMark && markCcounter == 1) {
            notBadStudents.add(student);
            result.append("Почти хорошист: " + student.getStudentName() + ", предмет - " + subject + "\n");
        }
    }


    private void countNotBadPupils(ArrayList<Student> students) {
        for (Student student : students) {
            getNotBadPupil(notBadStudents1stQ, excellentStudents1stQ, wellDoneStudents1stQ, student.getFirstQuarter(), result1stQuarter, student);
            getNotBadPupil(notBadStudents2ndQ, excellentStudents2ndQ, wellDoneStudents2ndQ, student.getSecondQuarter(), result2ndQuarter, student);
            getNotBadPupil(notBadStudents3rdQ, excellentStudents3rdQ, wellDoneStudents3rdQ, student.getThirdQuarter(), result3rdQuarter, student);
            getNotBadPupil(notBadStudents4thQ, excellentStudents4thQ, wellDoneStudents4thQ, student.getFourthQuarter(), result4thQuarter, student);
        }
    }

    private Integer getIntValue(Cell cell) {
        if (cell == null)
            return null;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
            return (int)cell.getNumericCellValue();
        else {
            if (cell.getStringCellValue() == null || cell.getStringCellValue().equals(""))
                return null;
            return Integer.parseInt(cell.getStringCellValue());
        }
    }

    public String getResult3rdQuarter() {
        if (result3rdQuarter == null || result3rdQuarter.toString().equals("")) {
            return "Отсутствуют данные";
        }
        return result3rdQuarter.toString();
    }

    public String getResult1stQuarter() {
        if (result1stQuarter == null || result1stQuarter.toString().equals("")) {
            return "Отсутствуют данные";
        }
        return result1stQuarter.toString();
    }


    public String getResult4thQuarter() {
        if (result4thQuarter == null || result4thQuarter.toString().equals("")) {
            return "Отсутствуют данные";
        }
        return result4thQuarter.toString();
    }

    public String getResultYear() {
        if (resultYear == null || resultYear.toString().equals("")) {
            return "Отсутствуют данные";
        }
        return resultYear.toString();
    }
    public String getResult2ndQuarter() {
        if (result2ndQuarter == null || result2ndQuarter.toString().equals("")) {
            return "Отсутствуют данные";
        }
        return result2ndQuarter.toString();
    }
}
