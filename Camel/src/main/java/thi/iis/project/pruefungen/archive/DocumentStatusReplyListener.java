package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Listens to documentStatusReply queue, processes the json and sends message to
 * camunda process with status of document_uploaded in table student_exam using
 * camunda rest api
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
                .setHeader("CamelHttpMethod", constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http4://localhost:8080/engine-rest/message");

    }

}
