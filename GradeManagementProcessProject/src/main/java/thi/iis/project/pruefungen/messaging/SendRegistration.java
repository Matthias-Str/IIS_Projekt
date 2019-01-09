package thi.iis.project.pruefungen.messaging;

import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.bean.Anmeldung;

public class SendRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Boolean sesa = (Boolean) execution.getVariable("participition_sesa");
        Boolean kao = (Boolean) execution.getVariable("participition_kao");
        Boolean iis = (Boolean) execution.getVariable("participition_iis");
        Boolean itim = (Boolean) execution.getVariable("participition_itim");

        IdentityService is = execution.getProcessEngineServices().getIdentityService();
        String username = is.getCurrentAuthentication().getUserId();

        Anmeldung registration = new Anmeldung(username, sesa, kao, iis, itim);
        
        sendMessage(registration, execution);
    }

    public void sendMessage(Anmeldung registration, DelegateExecution execution) {
        try {
            // Connection
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            factory.setTrustAllPackages(true);
            Connection connection = factory.createConnection();
            connection.start();

            // Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // Create Destination
            Destination destination = session.createQueue("registration_queue");
            
            // Create Message Producer
            MessageProducer producer = session.createProducer(destination);

            // Create Object Message
            ObjectMessage message = session.createObjectMessage(registration);
            
            // send message to queue
            producer.send(message);
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
