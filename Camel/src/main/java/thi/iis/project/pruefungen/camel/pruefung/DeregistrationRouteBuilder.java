package thi.iis.project.pruefungen.camel.pruefung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class DeregistrationRouteBuilder extends RouteBuilder {

    private static final String DEREGISTRATION_QUEUE = "deregistrationQueue";
    
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:" + DEREGISTRATION_QUEUE);
        
        from(source).log("Nachricht an Studentenamt weiterleiten: ${body}");
        // TODO Auto-generated method stub

    }

}
