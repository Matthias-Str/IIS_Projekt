package thi.iis.project.pruefungen.camel.anmeldung;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;

import thi.iis.project.pruefungen.pojos.Anmeldung;

/**
 * Processor that gets object of type Anmeldung an converts to json
 * 
 * @author Katrin Krüger
 *
 */
public class RegistrationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Anmeldung registration = exchange.getIn().getBody(Anmeldung.class);

        // input to xml
        StringWriter sw = new StringWriter();
        JAXB.marshal(registration, sw);
        String objectToXml = sw.toString();

        // xml to json
        JSONObject obj = XML.toJSONObject(objectToXml);

        exchange.getIn().setBody(obj.toString());
    }

}
