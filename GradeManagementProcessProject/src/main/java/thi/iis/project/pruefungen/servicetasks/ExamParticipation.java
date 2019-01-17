package thi.iis.project.pruefungen.servicetasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;

public class ExamParticipation implements JavaDelegate
{

    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        List<StudentExam> studentexamlist = (List<StudentExam>)execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_EXAM_LIST);
        Object participatedStudentCountObj = execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_PARTICIPATED_STUDENT_COUNT);
        int participatedStudentCount = 0;
        if(participatedStudentCountObj!=null)
        {
            participatedStudentCount = (int) participatedStudentCountObj;
        }
        execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_PARTICIPATED_STUDENT_COUNT);
        
        for(StudentExam se : studentexamlist)
        {
            if(se.getParticipated())
            {
                participatedStudentCount++;
                
            }
        }
        
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_EXAM_PARTICIPATION)
                            .setVariable(ValueIdentifiers.VALUE_IDENTIFIER_PARTICIPATED_STUDENT_COUNT, participatedStudentCount)
                            .correlateWithResult();

    }
    
}
