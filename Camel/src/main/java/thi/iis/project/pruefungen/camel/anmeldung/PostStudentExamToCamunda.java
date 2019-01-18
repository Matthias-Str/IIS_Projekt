package thi.iis.project.pruefungen.camel.anmeldung;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class PostStudentExamToCamunda extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:studentExamsAnswer_queue");

        from(source).process(new StudentExamDataProcessor()).setHeader("CamelHttpMethod", constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http4://localhost:8080/engine-rest/message");

    }
}
