package thi.iis.project.pruefungen.beans;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import thi.iis.project.pruefungen.webservices.Deadline;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;

@XmlRootElement(name="inputData")
public class InputData implements Serializable{
    Deadline[] deadlineList;
    Student[] studentList;
    Exam[] examList;
    
    public InputData(){
        
    }

    @XmlElement
    public Deadline[] getDeadlineList() {
        return deadlineList;
    }

    public void setDeadlineList(Deadline[] deadlineList) {
        this.deadlineList = deadlineList;
    }

    @XmlElement
    public Student[] getStudentList() {
        return studentList;
    }

    public void setStudentList(Student[] studentList) {
        this.studentList = studentList;
    }

    @XmlElement
    public Exam[] getExamList() {
        return examList;
    }

    public void setExamList(Exam[] examList) {
        this.examList = examList;
    }

    @Override
    public String toString() {
        return "InputData [deadlineList=" + Arrays.toString(deadlineList) + ", studentList="
                + Arrays.toString(studentList) + ", examList=" + Arrays.toString(examList) + "]";
    }
    
    
    
}
