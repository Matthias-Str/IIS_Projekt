package thi.iis.project.pruefungen.archiv;


import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Main Class to start for Archiv Project
 * @author Katrin Krüger
 *
 */
public class StartClass {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // start persist Document Listener
        PersistDocumentListener persistD = new PersistDocumentListener(session);
        persistD.startListener();
        
        // start socument status request listener
        DocumentStatusRequestListener documentL = new DocumentStatusRequestListener(session);
        documentL.startListener();
        
    }
}
