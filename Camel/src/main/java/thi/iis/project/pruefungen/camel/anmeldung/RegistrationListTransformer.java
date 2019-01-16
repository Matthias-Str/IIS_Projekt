package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Route Builder that gets Map of DeadlineNames and Dates and converts them to
 * xml preparedRegistration_queue
 * 
 * @author Katrin Krüger
 *
 */
public class RegistrationListTransformer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        Endpoint source = endpoint("jms:queue:rawRegistration_queue");
        Endpoint destination = endpoint("jms:queue:preparedRegistration_queue");

        from(source).process(new RegistrationProcessor()).to(destination);

    }

}
