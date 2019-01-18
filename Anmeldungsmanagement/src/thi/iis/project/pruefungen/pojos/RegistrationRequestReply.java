package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;

import thi.iis.project.pruefungen.webservices.StudentExam;

public class RegistrationRequestReply implements Serializable{

    private static final long serialVersionUID = 1L;

    String examId;
    StudentExam[] studentExamList;
    
    
    public RegistrationRequestReply() {
        super();
    }


    public RegistrationRequestReply(String examId, StudentExam[] studentExamList) {
        super();
        this.examId = examId;
        this.studentExamList = studentExamList;
    }


    public String getExamId() {
        return examId;
    }


    public void setExamId(String examId) {
        this.examId = examId;
    }


    public StudentExam[] getStudentExamList() {
        return studentExamList;
    }


    public void setStudentExamList(StudentExam[] studentExamList) {
        this.studentExamList = studentExamList;
    }
    
    
}
