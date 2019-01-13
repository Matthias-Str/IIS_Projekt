package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import thi.iis.project.pruefungen.webservices.Deadline;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;


@XmlRootElement(name="inputData")
public class InputData implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Deadline[] deadlineList;
    Student[] studentList;
    Exam[] examList;
    int numberOfStudents;
    Date firstExamDate;
    
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

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Date getFirstExamDate() {
        return firstExamDate;
    }

    public void setFirstExamDate(Date firstExamDate) {
        this.firstExamDate = firstExamDate;
    }

    
    
}
