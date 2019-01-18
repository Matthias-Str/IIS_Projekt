package thi.iis.project.pruefungen.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.StudentExam;
import thi.iis.project.pruefungen.jpa.services.StudentExamServiceLocal;

/**
 * WebService for StudentExam Modifications
 * 
 * @author Katrin Krüger
 *
 */
@WebService
public class StudentExamWebService {
    @Inject
    StudentExamServiceLocal studentExamService;

    /**
     * create a new StudentExam
     * 
     * @param studentExam
     */
    public void create(StudentExam studentExam) {
        studentExamService.create(studentExam);
    }
    


    //MHoepp
    public List<StudentExam> selectFromExam(Exam ex)
    {
        return studentExamService.selectFromExam(ex);
    }

    //MHoepp
    public List<StudentExam> selectAll()
    {
        return studentExamService.selectAll();
    }
}
