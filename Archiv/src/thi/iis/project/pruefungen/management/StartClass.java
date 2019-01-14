package thi.iis.project.pruefungen.management;


import java.io.StringWriter;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.xml.bind.JAXB;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class StartClass {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        PersistDocumentListener persistD = new PersistDocumentListener(session);
        persistD.startListener();
        
    }
}
