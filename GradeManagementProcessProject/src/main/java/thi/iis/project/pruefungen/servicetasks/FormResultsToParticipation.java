package thi.iis.project.pruefungen.servicetasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class FormResultsToParticipation implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        String examname = (String) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_EXAM_NAME);
        
        boolean kak_participated = (boolean) execution.getVariable("input_participation_kak");
        boolean mih_participated = (boolean) execution.getVariable("input_participation_mih");
        boolean mas_participated = (boolean) execution.getVariable("input_participation_mas");
        

        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        StudentExam[] studentExams = seWS.selectAll();
        List<StudentExam> participatedExamList = new ArrayList<StudentExam>();
        List<StudentExam> gradedExamList = new ArrayList<StudentExam>();
        
        for(StudentExam se : studentExams)
        {
            if(se.getExamId().getExamId().equals(examname) )
            {
                if(se.getRegistrationNumber().getRegistrationName().equals("katrin") && kak_participated)
                {
                    System.out.println(se.getRegistrationNumber().getRegistrationName()+ " participated in "+examname);
                    se.setParticipated(true);
                    participatedExamList.add(se);
                }else if(se.getRegistrationNumber().getRegistrationName().equals("michael") && mih_participated)
                {
                    System.out.println(se.getRegistrationNumber().getRegistrationName()+ " participated in "+examname);
                    se.setParticipated(true);
                    participatedExamList.add(se);
                }else if(se.getRegistrationNumber().getRegistrationName().equals("matthias") && mas_participated)
                {
                    System.out.println(se.getRegistrationNumber().getRegistrationName()+ " participated in "+examname);
                    se.setParticipated(true);
                    participatedExamList.add(se);
                }else
                {
                    System.out.println(se.getRegistrationNumber().getRegistrationName()+ " did not participate in "+examname);
                    se.setParticipated(false);
                }
                
            }
            System.out.println(se.getRegistrationNumber().getRegistrationName()+ " is not relevant for "+examname+" just for "+se.getExamId().getExamId());
        }
        execution.setVariable(ValueIdentifiers.VALUE_IDENTIFIER_PARTICIPATED_EXAM_LIST,participatedExamList);
        execution.setVariable(ValueIdentifiers.VALUE_IDENTIFIER_PARTICIPATED_STUDENT_COUNT,gradedExamList.size());
    }

}
