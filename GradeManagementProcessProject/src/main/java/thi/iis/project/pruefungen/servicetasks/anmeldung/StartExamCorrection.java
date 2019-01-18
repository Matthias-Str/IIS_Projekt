package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Exam;

/**
 * Sends message to starts one process per exam
 * 
 * @author Katrin Krüger
 *
 */
public class StartExamCorrection implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get current index and select user from studentList
        int index = (int) execution.getVariable("loopCounter");
        // get current exam
        Exam[] examList = (Exam[]) execution.getVariable("examListArray");
        Exam curExam = examList[index];

        Date announcement_date = (Date) execution.getVariable("announcement_date");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("exam", curExam);
        data.put("examId", curExam.getExamId());
        data.put("username", curExam.getProfessorId().getFirstname() + curExam.getProfessorId().getLastname());
        data.put("announcement_date", announcement_date);

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("startPruefungskorrektur").setVariables(data).correlateWithResult();
    }

}
