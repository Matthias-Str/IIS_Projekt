package thi.iis.project.pruefungen.camel.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Listens to documentStatusReply queue, processes the json to queue with name
 * of registrationName and examId
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusReplyListener extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:documentStatusReply_queue");

        from(source)
                .process(new DocumentStatusReplyProcessor())
                .recipientList(simple("jms:queue:${header.destination}"));

    }

}
