package thi.iis.project.pruefungen.Camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import thi.iis.project.pruefungen.archive.ArchiveListener;
import thi.iis.project.pruefungen.archive.ArchiveRouteBuilder;
import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.InitDataRouter;
import thi.iis.project.pruefungen.camel.anmeldung.PostInitDataToCamunda;
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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        connectionFactory.setTrustAllPackages(true);
        main.bind("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        main.addRouteBuilder(new DateListSplitter());
        main.addRouteBuilder(new InitDataRouter());
        main.addRouteBuilder(new RegistrationListTransformer());
        main.addRouteBuilder(new PostInitDataToCamunda());
        main.addRouteBuilder(new ArchiveListener());
        main.addRouteBuilder(new ArchiveRouteBuilder());
        main.run(args);
    }

}

