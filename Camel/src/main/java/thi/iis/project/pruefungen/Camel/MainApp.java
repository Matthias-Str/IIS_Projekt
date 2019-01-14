package thi.iis.project.pruefungen.Camel;

import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp2.BasicDataSource;

import thi.iis.project.pruefungen.camel.anmeldung.DateListSplitter;
import thi.iis.project.pruefungen.camel.anmeldung.InitDataRouter;
import thi.iis.project.pruefungen.camel.anmeldung.RegistrationListTransformer;
import thi.iis.project.pruefungen.camel.pruefung.ThirdTryRouter;

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
        
        main.bind("pruefungDataSource", setupDataSource("jdbc:mysql://localhost:3306/grade_management"));
        
        main.addRouteBuilder(new DateListSplitter());
        main.addRouteBuilder(new InitDataRouter());
        main.addRouteBuilder(new RegistrationListTransformer());
        main.addRouteBuilder(new PostToCamunda());
        
        //Prüfungskontrolle
        
        main.addRouteBuilder(new ThirdTryRouter());
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

