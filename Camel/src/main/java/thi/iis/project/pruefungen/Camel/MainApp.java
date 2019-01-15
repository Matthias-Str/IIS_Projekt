package thi.iis.project.pruefungen.Camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import thi.iis.project.pruefungen.archive.ArchiveListener;
import thi.iis.project.pruefungen.archive.ArchiveRouteBuilder;
import thi.iis.project.pruefungen.archive.DocumentStatusReplyListener;
import thi.iis.project.pruefungen.archive.DocumentStatusRequestListener;
import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.InitDataRouter;
import thi.iis.project.pruefungen.camel.anmeldung.PostInitDataToCamunda;
import thi.iis.project.pruefungen.camel.anmeldung.RegistrationListTransformer;

/**
 * Main Class to run Camel Application
 * 
 * @author Katrin Krüger
 */
public class MainApp {


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
        main.addRouteBuilder(new DocumentStatusRequestListener());
        main.addRouteBuilder(new DocumentStatusReplyListener());
        main.run(args);
    }

}

