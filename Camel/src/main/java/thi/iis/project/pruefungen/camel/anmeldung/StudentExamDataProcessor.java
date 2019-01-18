package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import thi.iis.project.pruefungen.pojos.CamundaRESTJSON;
import thi.iis.project.pruefungen.pojos.CorrelationKey;
import thi.iis.project.pruefungen.pojos.ProcessVariable;
import thi.iis.project.pruefungen.pojos.RegistrationRequestReply;
import thi.iis.project.pruefungen.pojos.ValueType;

/**
 * Processor that receives a reply with studentExam list fr one exam and posts
 * that directly to the corresponding process
 * 
 * @author Katrin Krüger
 *
 */
public class StudentExamDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        RegistrationRequestReply replyContent = exchange.getIn().getBody(RegistrationRequestReply.class);

        // init new object that is send back to camunda later
        CamundaRESTJSON newData = new CamundaRESTJSON();

        // set message name
        newData.setMessageName("receiveStudentExams");
        newData.setResultEnabled(true);

        // correlate by examId
        newData.getCorrelationKeys()
                .add(new CorrelationKey("examId", new ValueType(replyContent.getExamId(), "String")));

        // set data
        if (replyContent.getStudentExamList() != null){
            newData.getProcessVariables().add(new ProcessVariable("studentExams",
                    new ValueType(replyContent.studentExamListToJson().toString(), "String")));
            
        }else{
            newData.getProcessVariables().add(new ProcessVariable("studentExams",
                    new ValueType("", "String")));
        }

        Thread.sleep(10000);
        System.out.println(newData.toJson().toString());
        System.out.println(replyContent.getExamId());

        // write data back to message body
        exchange.getIn().setBody(newData.toJson());
    }

}
