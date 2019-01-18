package thi.iis.project.pruefungen.jpa.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Exam;

/**
 * Local Service Interface for Exam Service
 * 
 * @author Katrin Krüger
 *
 */
@Local
public interface ExamServiceLocal {
    /**
     * update existing exam
     * 
     * @param Exam
     * @return Exam
     */
    public Exam update(Exam e);

    /**
     * selct exam by name
     * 
     * @param name
     * @return Exam
     */
    public Exam selectByName(String name);

    /**
     * select all exams
     * 
     * @return List<Exam>
     */
    public List<Exam> selectAll();

    /**
     * get frist exam date
     * 
     * @return java.util.Date
     */
    public Date getFirstExamDate();
}
