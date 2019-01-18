package thi.iis.project.pruefungen.Camel;


import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp2.BasicDataSource;

import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.DatePersistedAckAggregator;
import thi.iis.project.pruefungen.camel.anmeldung.PostInitDataToCamunda;
import thi.iis.project.pruefungen.camel.anmeldung.RegistrationListTransformer;
import thi.iis.project.pruefungen.camel.archive.ArchiveListener;
import thi.iis.project.pruefungen.camel.archive.ArchiveRouteBuilder;
import thi.iis.project.pruefungen.camel.archive.DocumentStatusReplyListener;
import thi.iis.project.pruefungen.camel.archive.DocumentStatusRequestListener;
import thi.iis.project.pruefungen.camel.pruefung.DeregistrationRouteBuilder;
import thi.iis.project.pruefungen.camel.pruefung.ThirdTryRouter;


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
        
        main.bind("pruefungDataSource", setupDataSource("jdbc:mysql://localhost:3306/grade_management"));
        
        main.addRouteBuilder(new DateListSplitter());
        main.addRouteBuilder(new RegistrationListTransformer());
        main.addRouteBuilder(new PostInitDataToCamunda());
        main.addRouteBuilder(new ArchiveListener());
        main.addRouteBuilder(new ArchiveRouteBuilder());
        main.addRouteBuilder(new DocumentStatusRequestListener());
        main.addRouteBuilder(new DocumentStatusReplyListener());
        main.addRouteBuilder(new DatePersistedAckAggregator());

        
        //Prüfungskontrolle
        main.addRouteBuilder(new ThirdTryRouter());
        main.addRouteBuilder(new DeregistrationRouteBuilder());

        main.run(args);
    }
    
    private static DataSource setupDataSource(String connectionUrl){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("master42");
        ds.setUrl(connectionUrl);
        return ds;
    }

}

