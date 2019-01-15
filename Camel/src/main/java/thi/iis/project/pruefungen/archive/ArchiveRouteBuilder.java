package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * Route Builder listens to upload directory and sends file to archive directory
 * and database directory
 * 
 * @author Katrin Krüger
 *
 */
public class ArchiveRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("file:/home/lars/pruefungen_upload");
        Endpoint destination_archive = endpoint("file:/home/lars/pruefungen_archive");
        Endpoint destination_database = endpoint("file:/home/lars/pruefungen_database");

        from(source)
            .multicast()
            .to(destination_archive, destination_database);

    }

}
