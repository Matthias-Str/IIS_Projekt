package thi.iis.project.pruefungen.camel.pruefung;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import thi.iis.project.pruefungen.webservices.StudentExam;

public class ThirdTryRouter extends RouteBuilder {
    
    private static final String THIRD_TRY_QUEUE = "thirdTryQueue";

    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:" + THIRD_TRY_QUEUE);
        
        JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
        JAXBContext con = JAXBContext.newInstance(StudentExam.class);
        xmlDataFormat.setContext(con);
        
        from(source).log("${body}").unmarshal(xmlDataFormat)
            .process(new ThirdTryJDBCProcessor())
            .log("${body}")
            .to("jdbc:pruefungDataSource")
            .process(new TryCountProcessor()).log("Answer: ${body}");
    }

}
