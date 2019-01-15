package thi.iis.project.pruefungen.archive;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * RouteBuilder listens to directory database (contains files where status of
 * upload in table student_exam is not set to true yet sends filename to
 * archive_queue
 * 
 * @author Katrin Krüger
 *
 */
public class ArchiveListener extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("file:/home/lars/pruefungen_database");
        Endpoint destination = endpoint("jms:queue:archive_queue");

        from(source)
            .process(new ArchiveProcessor())
            .to(destination);
    }

}
