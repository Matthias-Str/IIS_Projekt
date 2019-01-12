package thi.iis.project.pruefungen.Camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.InitDataRouter;
import thi.iis.project.pruefungen.camel.anmeldung.RegistrationListTransformer;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        main.bind("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        main.addRouteBuilder(new DateListSplitter());
        main.addRouteBuilder(new InitDataRouter());
        main.addRouteBuilder(new RegistrationListTransformer());
        main.run(args);
    }

}

