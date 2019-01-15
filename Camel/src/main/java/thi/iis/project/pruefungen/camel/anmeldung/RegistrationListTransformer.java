package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;

/**
 * Processor that forwards message from rawRegistration_queue to
 * preparedRegistration_queue
 * 
 * @author Katrin Krüger
 *
 */
public class RegistrationListTransformer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        Endpoint source = endpoint("jms:queue:rawRegistration_queue");
        Endpoint destination = endpoint("jms:queue:preparedRegistration_queue");


        from(source)
                .to(destination);

    }

}
