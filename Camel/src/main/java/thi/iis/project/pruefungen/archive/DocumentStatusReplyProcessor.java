package thi.iis.project.pruefungen.archive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.pojos.CamundaRESTJSON;
import thi.iis.project.pruefungen.pojos.CorrelationKey;
import thi.iis.project.pruefungen.pojos.ProcessVariable;
import thi.iis.project.pruefungen.pojos.ValueType;

public class DocumentStatusReplyProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        String in = exchange.getIn().getBody(String.class);
        JSONObject input = new JSONObject(in);
        
        CamundaRESTJSON newData = new CamundaRESTJSON();
        
        // set message name
        newData.setMessageName("receiveDocumentState");
        newData.setResultEnabled(true);
        
        // add correlationKeys
        String registrationName = input.getString("registrationName");
        newData.getCorrelationKeys().add(new CorrelationKey("registrationName", new ValueType(registrationName, "String")));
        String examId = input.getString("examId");
        newData.getCorrelationKeys().add(new CorrelationKey("examId", new ValueType(examId, "String")));

        // add process variable
        Boolean status = input.getBoolean("status");
        newData.getProcessVariables().add(new ProcessVariable("documentUploadStatus", new ValueType(status, "Boolean")));
        
        // write data back to message body
        exchange.getIn().setBody(newData.toJson());
        
    }

}
