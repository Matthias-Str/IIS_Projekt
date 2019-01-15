package thi.iis.project.pruefungen.archive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.pojos.DocumentStatusRequest;

public class DocumentStatusRequestProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        DocumentStatusRequest request = exchange.getIn().getBody(DocumentStatusRequest.class);
        
        JSONObject json = request.toJson();
        
        exchange.getIn().setBody(json.toString());
    }

}
