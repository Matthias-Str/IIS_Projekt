package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Listens to queue an waits for 8 incoming messages before sending ack to
 * another queue
 * 
 * @author Katrin Krüger
 *
 */
public class DatePersistedAckAggregator extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:datePersistedAck_queue");
        Endpoint destination = endpoint("jms:queue:getInitData_queue");

        from(source).aggregate(constant(true), new StringAggregationStrategy()).completionSize(8).to(destination);

    }

}
