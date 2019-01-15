package thi.iis.project.pruefungen.archive;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ArchiveProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        
        // extract filename
        String filename = file.getName();
        String result = filename.substring(0, filename.lastIndexOf('.'));
        System.out.println(result + " send to queue");
        
        exchange.getIn().setBody(result);
        
    }

}
