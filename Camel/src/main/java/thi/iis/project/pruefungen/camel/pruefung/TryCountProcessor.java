package thi.iis.project.pruefungen.camel.pruefung;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TryCountProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().getBody();
    }

}
