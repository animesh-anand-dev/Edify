package com.edify.app;

public class Students {

    private String studentName;
    private String studentGender;
    private String studentClass;
    private String studentBoard;
    private String studentSubjects;

    public Students() {}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentBoard() {
        return studentBoard;
    }

    public void setStudentBoard(String studentBoard) {
        this.studentBoard = studentBoard;
    }

    public String getStudentSubjects() {
        return studentSubjects;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.studentSubjects = teacherSubject;
    }
}
