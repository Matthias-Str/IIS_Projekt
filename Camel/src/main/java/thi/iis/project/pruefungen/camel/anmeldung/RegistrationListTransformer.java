package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;

public class RegistrationListTransformer extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        Endpoint source = endpoint("jms:queue:rawRegistration_queue");
        Endpoint destination = endpoint("jms:queue:preparedRegistration_queue");

        // XML Data Format
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setForceTopLevelObject(false);
        xmlJsonFormat.setSkipWhitespace(true);
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setSkipNamespaces(true);
        xmlJsonFormat.setRemoveNamespacePrefixes(true);
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setRootName("anmeldung");
             
        
        from(source)       
//            .log("before  ${body}")
//            .marshal(xmlJsonFormat)
//            .log("after  ${body}")
            .to(destination); 

        
    }

}
