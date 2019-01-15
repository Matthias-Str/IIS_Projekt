package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Route BUilder that listens to request queue, transforms message content
 * (Object of type DocumentStatusRequest) to json
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusRequestListener extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:documentStatusRequest_queue");
        Endpoint destination = endpoint("jms:queue:documentStatusRequestArchiv_queue");

        from(source)
                .process(new DocumentStatusRequestProcessor())
                .to(destination);

    }

}
