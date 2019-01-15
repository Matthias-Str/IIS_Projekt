package thi.iis.project.pruefungen.webservices;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.services.ExamService;

/**
 * WebService for Exam Modifications
 * 
 * @author Katrin Krüger
 *
 */
@WebService
public class ExamWebService {

    @Inject
    ExamService examService;

    /**
     * update teh date of an existing exam
     * 
     * @param name
     * @param date
     * @return Exam
     */
    public Exam updateExamdate(String name, Date date) {
        Exam exam = examService.selectByName(name);
        exam.setDate(date);
        examService.update(exam);
        return exam;
    }

    /**
     * select all exams
     * 
     * @return List<Exam>
     */
    public List<Exam> selectAll() {
        return examService.selectAll();
    }

    /**
     * select an exam by its name
     * 
     * @param name
     * @return Exam
     */
    public Exam selectByName(String name) {
        return examService.selectByName(name);
    }

    /**
     * select the first exam date
     * 
     * @return
     */
    public Date getFirstExamDate() {
        return examService.getFirstExamDate();
    }
}
