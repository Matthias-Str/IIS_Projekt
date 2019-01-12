package thi.iis.project.pruefungen.webservices;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.StudentExam;
import thi.iis.project.pruefungen.jpa.services.StudentExamServiceLocal;

/**
 * WebService for StudentExam Modifications 
 * @author Katrin Krüger
 *
 */
@WebService
public class StudentExamWebService {
    @Inject
    StudentExamServiceLocal studentExamService;
    
    public void create(StudentExam se){
        studentExamService.create(se);
    }
}
