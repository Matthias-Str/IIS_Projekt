package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class ArchiveListener extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("file:/home/lars/pruefungen_archive?noop=true");
        Endpoint destination = endpoint("jms:queue:archive_queue");
        
        from(source)
            .process(new ArchiveProcessor())
            .to(destination);
    }

}
