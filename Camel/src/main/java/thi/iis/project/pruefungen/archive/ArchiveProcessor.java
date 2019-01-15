package thi.iis.project.pruefungen.archive;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor that gets file and sends name of file (without ending to queue)
 * 
 * @author Katrin Krüger
 *
 */
public class ArchiveProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // get file that was forwarded to queue
        File file = exchange.getIn().getBody(File.class);

        // extract filename
        String filename = file.getName();
        // remove ending of filename
        String result = filename.substring(0, filename.lastIndexOf('.'));

        // set result filename as message body
        exchange.getIn().setBody(result);

    }

}
