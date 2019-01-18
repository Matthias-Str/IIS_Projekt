package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

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

    /**
     * select student exam by exam is
     * @param examId
     * @return List<StudentExam>
     */
    List<StudentExam> selectByExamId(String examId);
}
