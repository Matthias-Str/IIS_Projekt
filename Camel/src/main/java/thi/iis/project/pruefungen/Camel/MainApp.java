package thi.iis.project.pruefungen.Camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.DatePersistedAckAggregator;
import thi.iis.project.pruefungen.camel.anmeldung.PostInitDataToCamunda;
import thi.iis.project.pruefungen.camel.anmeldung.RegistrationListTransformer;
import thi.iis.project.pruefungen.camel.archive.ArchiveListener;
import thi.iis.project.pruefungen.camel.archive.ArchiveRouteBuilder;
import thi.iis.project.pruefungen.camel.archive.DocumentStatusReplyListener;
import thi.iis.project.pruefungen.camel.archive.DocumentStatusRequestListener;

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
        main.addRouteBuilder(new RegistrationListTransformer());
        main.addRouteBuilder(new PostInitDataToCamunda());
        main.addRouteBuilder(new ArchiveListener());
        main.addRouteBuilder(new ArchiveRouteBuilder());
        main.addRouteBuilder(new DocumentStatusRequestListener());
        main.addRouteBuilder(new DocumentStatusReplyListener());
        main.addRouteBuilder(new DatePersistedAckAggregator());
        main.run(args);
    }

}

