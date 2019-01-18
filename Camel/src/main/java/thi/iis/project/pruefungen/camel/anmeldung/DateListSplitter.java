package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Router that splits message wit registration dates in multiple messages with
 * each one date
 * 
 * @author Katrin Krüger
 *
 */
public class DateListSplitter extends RouteBuilder {

    public void configure() {

        Endpoint source = endpoint("jms:queue:rawRegistrationDates_queue");
        Endpoint destination = endpoint("jms:queue:preparedRegistrationDates_queue");

        from(source)
                .process(new RegistrationDateProcessor())
                .split()
                .tokenizeXML("entry")
                .log("${body}")
                .to(destination);
    }
}
