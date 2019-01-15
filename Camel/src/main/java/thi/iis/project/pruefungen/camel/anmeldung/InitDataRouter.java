package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Router that listens to rawInitDataqueue and forawards the message to
 * preparedInitData_queueu
 * 
 * @author Katrin Krüger
 *
 */
public class InitDataRouter extends RouteBuilder {

    public void configure() {

        Endpoint source = endpoint("jms:queue:rawInitData_queue");
        Endpoint destination = endpoint("jms:queue:preparedInitData_queue");

        from(source).log("${body}").to(destination);
    }

}
