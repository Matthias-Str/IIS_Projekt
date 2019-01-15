package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.pojos.Anmeldung;

/**
 * implementation of task "Abschicken der Anmeldung", sends list of
 * registrations to queue to persist them
 * 
 * @author Katrin Krüger
 *
 */
public class SendRegistration implements JavaDelegate {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server
    // is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "rawRegistration_queue"; // Queue Name

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get current username
        String username = (String) execution.getVariable("username");
        // get registrations
        Boolean sesa = (Boolean) execution.getVariable("inf_m_sesa_ws18_registration");
        Boolean kao = (Boolean) execution.getVariable("inf_m_kao_ws18_registration");
        Boolean iis = (Boolean) execution.getVariable("inf_m_iis_ws18_registration");
        Boolean itim = (Boolean) execution.getVariable("inf_m_itim_ws18_registration");

        Map<String, Boolean> registrationList = new HashMap<>();
        registrationList.put("inf_m_sesa_ws18", sesa);
        registrationList.put("inf_m_kao_ws18", kao);
        registrationList.put("inf_m_iis_ws18", iis);
        registrationList.put("inf_m_itim_ws18", itim);

        Anmeldung registration = new Anmeldung(username, registrationList);

        // send registration to registration_queue
        sendToQueue(execution, registration);

    }

    /**
     * send registration to queue as xml
     * 
     * @param execution
     * @param registration
     * @throws JMSException
     */
    private void sendToQueue(DelegateExecution execution, Anmeldung registration) throws JMSException {
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
        StringWriter sw = new StringWriter();
        JAXB.marshal(registration, sw);
        String objectToXml = sw.toString();
        TextMessage message = session.createTextMessage(objectToXml);

        // Send message to queue
        producer.send(message);

        connection.close();
    }
}
