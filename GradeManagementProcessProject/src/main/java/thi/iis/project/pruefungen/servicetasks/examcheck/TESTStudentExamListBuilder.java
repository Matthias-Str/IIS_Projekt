package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Professor;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class TESTStudentExamListBuilder implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Professor testprof = new Professor();
        
        testprof.setFirstname("Ulrich");
        testprof.setLastname("Schmidt");
        
        Exam testExam = new Exam();
        testExam.setExamId("inf_m_kao_ws18");
        testExam.setProfessorId(testprof);

        Student testStudent = new Student();
        testStudent.setRegistrationName("matthias");
        
        StudentExam testStudentExam = new StudentExam();
        testStudentExam.setExamId(testExam);
        testStudentExam.setRegistrationNumber(testStudent);
        testStudentExam.setGrade(BigDecimal.valueOf(5));
        testStudentExam.setStudentExamId(0);
        
        Collection<StudentExam> list = new ArrayList<>();
        list.add(testStudentExam);
        
        System.out.println("CALLLED THIS TASK!!!!!1");
        
        execution.setVariable("studentexamlist", list);
    }

}
