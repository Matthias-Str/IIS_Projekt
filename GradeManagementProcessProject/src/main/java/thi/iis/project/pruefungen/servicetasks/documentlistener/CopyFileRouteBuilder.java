package thi.iis.project.pruefungen.servicetasks.documentlistener;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class CopyFileRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("file:/home/lars/pruefungen_upload?delete=true");
        Endpoint destination = endpoint("file:/home/lars/pruefungen_archive");
        
        from(source)                //FileConsumer
            .log("${body}")
            .to(destination);       // FileProducer
    }

}
