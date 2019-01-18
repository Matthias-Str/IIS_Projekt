package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.StudentExam;

/**
 * Local Service Interface for StudentExam Service
 * 
 * @author Katrin Krüger
 *
 */
@Local
public interface StudentExamServiceLocal {
    /**
     * create new StudentExam
     * 
     * @param studentExam
     */
    public void create(StudentExam se);
  //MHoepp
    public List<StudentExam> selectAll();
  //MHoepp
    public List<StudentExam> selectFromExam(Exam ex);
}
