package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class ArchiveRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("file:/home/lars/pruefungen_upload");
        Endpoint destination = endpoint("file:/home/lars/pruefungen_archive");
        
        from(source)
            .to(destination);
        
    }

}
