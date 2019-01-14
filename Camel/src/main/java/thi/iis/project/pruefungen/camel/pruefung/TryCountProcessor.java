package thi.iis.project.pruefungen.camel.pruefung;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TryCountProcessor implements Processor {
    
    final static int MAX_TRIES = 3;

    @Override
    public void process(Exchange exchange) throws Exception {
        String databaseReply = exchange.getIn().getBody().toString();
        
        if(databaseReply.contains("tries")){
            int triesIndex = databaseReply.indexOf("=");
            exchange.getIn().setBody(String.valueOf(Integer.parseInt(databaseReply.substring(triesIndex + 1, triesIndex + 2)) >= MAX_TRIES));
        }
    }

}
