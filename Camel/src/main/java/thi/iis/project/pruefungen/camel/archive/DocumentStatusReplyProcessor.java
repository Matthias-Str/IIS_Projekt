package thi.iis.project.pruefungen.camel.archive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

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
        
        String examId = input.getString("examId");
        String registrationName = input.getString("registrationName");
        
        exchange.getIn().setBody(in);
        exchange.getIn().setHeader("destination", examId + "_" + registrationName);

    }

}
