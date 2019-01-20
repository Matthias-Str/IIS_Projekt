package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.math.BigDecimal;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class SaveNewGradeTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        BigDecimal newGrade = BigDecimal.valueOf(Double.parseDouble((String) execution.getVariable("newGrade")));
        StudentExam studentExam = (StudentExam) execution.getVariable("studentExam");
        
        System.out.println("NewGrade: " + newGrade + ", StudentExam: " + studentExam);
        
        if(!newGrade.equals(BigDecimal.valueOf(5.0d))){
            studentExam.setGrade(newGrade);
            seWS.update(studentExam);
            execution.setVariable("gradeChanged", true);
            System.out.println("gradeChanged: true");
        } else {
            execution.setVariable("gradeChanged", false);
            System.out.println("gradeChanged: false");
        }
        
    }

}
