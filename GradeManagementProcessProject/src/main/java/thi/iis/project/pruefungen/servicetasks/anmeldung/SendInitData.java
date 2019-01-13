package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.pojos.InputData;
import thi.iis.project.pruefungen.webservices.Deadline;

public class SendInitData implements JavaDelegate{

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        InputData inputData = (InputData) execution.getVariable("inputData");
        // send message
        Map<String, Object> data = setData(inputData, execution);

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("receiveInitData").setVariables(data).correlateWithResult();
    }
    
    private Map<String, Object> setData(InputData inputData, DelegateExecution execution){
        Map<String, Object> result = new HashMap<String, Object>();
        
        // set deadlines
        Deadline[] deadlineList = inputData.getDeadlineList();
        for(Deadline d : deadlineList){
            result.put(d.getDeadlineName(), d.getDate().getTime());
        }
        // set examList
        result.put("examList", inputData.getExamList());
        // set studentList
        result.put("studentList", inputData.getStudentList());
        // set first exam date
        result.put("firstExamDate", inputData.getFirstExamDate());
        // set number of students
        result.put("numberOfStudents", inputData.getNumberOfStudents());
        
        return result;
    }

}
