package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.StudentExam;

@Local
public interface StudentExamServiceLocal {
    public void create(StudentExam se);
    public List<StudentExam> selectAll();
    public List<StudentExam> selectFromExam(Exam ex);
}
