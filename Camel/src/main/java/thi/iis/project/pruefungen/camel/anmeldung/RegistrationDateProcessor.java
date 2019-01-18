package thi.iis.project.pruefungen.camel.anmeldung;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import thi.iis.project.pruefungen.pojos.DateList;

/**
 * Processor that gets a map of deadlineNames and Dates and converts them to xml
 * 
 * @author Katrin Krüger
 *
 */
public class RegistrationDateProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // get message body as object of type InputData
        Map<String, String> in = exchange.getIn().getBody(Map.class);

        DateList dateList = new DateList();
        dateList.setDateList(in);

        StringWriter sw = new StringWriter();
        JAXB.marshal(dateList, sw);
        String objectToXml = sw.toString();

        exchange.getIn().setBody(objectToXml);
    }

}
