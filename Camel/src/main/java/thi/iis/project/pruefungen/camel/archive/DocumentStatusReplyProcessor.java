package thi.iis.project.pruefungen.camel.archive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.pojos.CamundaRESTJSON;
import thi.iis.project.pruefungen.pojos.CorrelationKey;
import thi.iis.project.pruefungen.pojos.ProcessVariable;
import thi.iis.project.pruefungen.pojos.ValueType;

/**
 * Processor that gets json as input and extracts registrationName, examId and
 * status and defines message format that is send to camunda
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusReplyProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // get string from message body and convert to json object
        String in = exchange.getIn().getBody(String.class);
        JSONObject input = new JSONObject(in);

        // init new object that gets message body and is send to camunda rest
        // api
        CamundaRESTJSON camundaData = new CamundaRESTJSON();

        // set message name
        camundaData.setMessageName("receiveDocumentState");
        camundaData.setResultEnabled(true);

        // add correlationKeys
        String registrationName = input.getString("registrationName");
        camundaData.getCorrelationKeys()
                .add(new CorrelationKey("registrationName", new ValueType(registrationName, "String")));
        String examId = input.getString("examId");
        camundaData.getCorrelationKeys().add(new CorrelationKey("examId", new ValueType(examId, "String")));

        // add process variable
        Boolean status = input.getBoolean("status");
        camundaData.getProcessVariables()
                .add(new ProcessVariable("documentUploadStatus", new ValueType(status, "Boolean")));

        // write data back to message body
        exchange.getIn().setBody(camundaData.toJson());

    }

}
