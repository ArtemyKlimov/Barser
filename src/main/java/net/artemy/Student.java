package net.artemy;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Integer studentNum;
    private String studentName;
    private Period firstQuarter;
    private Period secondQuarter;
    private Period thirdQuarter;
    private Period fourthQuarter;
    private Period interimCertification;
    private Period interimCertificationDO1;
    private Period interimCertificationDO2;
    private Period year;
    private Period exams;
    private Period finalCertification;



    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Period getFirstQuarter() {
        return firstQuarter;
    }
    public void setFirstQuarter(Period firstQuarter) {
        this.firstQuarter = firstQuarter;
    }
    public Period getSecondQuarter() {
        return secondQuarter;
    }
    public void setSecondQuarter(Period secondQuarter) {
        this.secondQuarter = secondQuarter;
    }
    public Period getThirdQuarter() {
        return thirdQuarter;
    }

    public void setThirdQuarter(Period thirdQuarter) {
        this.thirdQuarter = thirdQuarter;
    }

    public Period getFourthQuarter() {
        return fourthQuarter;
    }

    public void setFourthQuarter(Period fourthQuarter) {
        this.fourthQuarter = fourthQuarter;
    }

    public Period getInterimCertification() {
        return interimCertification;
    }

    public void setInterimCertification(Period interimCertification) {
        this.interimCertification = interimCertification;
    }

    public Period getInterimCertificationDO1() {
        return interimCertificationDO1;
    }

    public void setInterimCertificationDO1(Period interimCertificationDO1) {
        this.interimCertificationDO1 = interimCertificationDO1;
    }

    public Period getInterimCertificationDO2() {
        return interimCertificationDO2;
    }

    public void setInterimCertificationDO2(Period interimCertificationDO2) {
        this.interimCertificationDO2 = interimCertificationDO2;
    }

    public Period getYear() {
        return year;
    }

    public void setYear(Period year) {
        this.year = year;
    }

    public Period getExams() {
        return exams;
    }

    public void setExams(Period exams) {
        this.exams = exams;
    }

    public Period getFinalCertification() {
        return finalCertification;
    }

    public void setFinalCertification(Period finalCertification) {
        this.finalCertification = finalCertification;
    }
}
