package thi.iis.project.pruefungen.main;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import thi.iis.project.pruefungen.anmeldung.GetInitDataListener;
import thi.iis.project.pruefungen.anmeldung.NewDateListener;
import thi.iis.project.pruefungen.anmeldung.RegistrationListener;
import thi.iis.project.pruefungen.anmeldung.RequestRegistrationsListener;

/**
 * Main Class of Anmeldungsmanagement
 * 
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
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // start NewDateListener
        NewDateListener ndListener = new NewDateListener(session);
        ndListener.startListener();

        // start DatePersistedAckListener
        GetInitDataListener dpAckListener = new GetInitDataListener(session);
        dpAckListener.startListener();

        // start RegistrationListener
        RegistrationListener regListener = new RegistrationListener(session);
        regListener.startListener();
        
        // start RequestRegistrationListener
        RequestRegistrationsListener reqListener = new RequestRegistrationsListener(session);
        reqListener.startListener();
    }
}
