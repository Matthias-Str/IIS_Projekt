package thi.iis.project.pruefungen.servicetasks.anmeldung;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * request all registrations for ws 18
 * 
 * @author Katrin Krüger
 *
 */
public class RequestRegistrations implements JavaDelegate {
    // broker url
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // queue name
    private static String subject = "requestRegistrations_queue"; 
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Get JMS connection from the server and start it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create Queue (if it doesn't already exist)
        Destination destination = session.createQueue(subject);

        // Create message producer
        MessageProducer producer = session.createProducer(destination);

        // Create messages
        String examId = (String) execution.getVariable("examId");
        
        TextMessage message = session.createTextMessage(examId);
        System.out.println(examId);

        // Send message to queue
        producer.send(message);

        connection.close();
        
    }

}
