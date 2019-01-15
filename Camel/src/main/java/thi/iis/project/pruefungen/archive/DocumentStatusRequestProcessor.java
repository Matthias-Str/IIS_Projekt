package thi.iis.project.pruefungen.archive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.pojos.DocumentStatusRequest;

/**
 * Processor that transforms object of type DocumentStatusRequest to json
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusRequestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // get DocumentStatusRequest object from message body
        DocumentStatusRequest request = exchange.getIn().getBody(DocumentStatusRequest.class);

        // converte object to json
        JSONObject json = request.toJson();

        // set jsonObject as message body
        exchange.getIn().setBody(json.toString());
    }

}
