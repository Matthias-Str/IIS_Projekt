package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import thi.iis.project.pruefungen.pojos.CamundaRESTJSON;
import thi.iis.project.pruefungen.pojos.ExamList;
import thi.iis.project.pruefungen.pojos.InputData;
import thi.iis.project.pruefungen.pojos.ProcessVariable;
import thi.iis.project.pruefungen.pojos.StudentList;
import thi.iis.project.pruefungen.pojos.ValueType;
import thi.iis.project.pruefungen.webservices.Deadline;

public class InitDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        InputData in = exchange.getIn().getBody(InputData.class);
        System.out.println(in);

        CamundaRESTJSON newData = new CamundaRESTJSON();
        
        // set message name
        newData.setMessageName("receiveInitData");
        newData.setResultEnabled(true);
        
        // set deadlines
        Deadline[] deadlineList = in.getDeadlineList();
        for(Deadline d : deadlineList){
            newData.getProcessVariables().add(new ProcessVariable(d.getDeadlineName(), new ValueType(d.getDate(), "Date")));
        }
        // set examlist
        ExamList examList = new ExamList();
        examList.setExams(in.getExamList());
        newData.getProcessVariables().add(new ProcessVariable("examList", new ValueType(examList.toJson().toString(), "String")));
        // set student List
        StudentList studentList = new StudentList();
        studentList.setStudents(in.getStudentList());
        newData.getProcessVariables().add(new ProcessVariable("studentList", new ValueType(studentList.toJson().toString(), "String")));
        // set number of students
        newData.getProcessVariables().add(new ProcessVariable("numberOfStudents", new ValueType(in.getNumberOfStudents(), "Integer")));
        // set first exam date
        newData.getProcessVariables().add(new ProcessVariable("firstExamDate", new ValueType(in.getFirstExamDate(), "Date")));
        // write data back to message body
        exchange.getIn().setBody(newData.toJson());

    }

}
