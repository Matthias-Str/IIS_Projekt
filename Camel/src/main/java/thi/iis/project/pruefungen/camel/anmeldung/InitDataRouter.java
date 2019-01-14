package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class InitDataRouter extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

            Endpoint source = endpoint("jms:queue:rawInitData_queue");
            Endpoint destination = endpoint("jms:queue:preparedInitData_queue");
            
            from(source)
                .log("${body}")
                .to(destination);
        }

}
