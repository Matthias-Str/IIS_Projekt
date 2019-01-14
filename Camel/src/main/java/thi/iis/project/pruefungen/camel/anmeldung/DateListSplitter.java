package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class DateListSplitter extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        Endpoint source = endpoint("jms:queue:rawRegistrationDates_queue");
        Endpoint destination = endpoint("jms:queue:preparedRegistrationDates_queue");

        from(source).split().tokenizeXML("entry").log("${body}").to(destination);
    }
}
