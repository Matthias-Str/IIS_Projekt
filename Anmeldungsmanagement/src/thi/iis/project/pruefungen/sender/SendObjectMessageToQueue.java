package thi.iis.project.pruefungen.sender;

import java.io.Serializable;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class SendObjectMessageToQueue {

    public SendObjectMessageToQueue() {

    }

    public void sendMessage(Session session, String subject, Serializable object) throws JMSException {
        // Create Queue (if it doesn't already exist)
        Destination destination = session.createQueue(subject);

        // Create message producer
        MessageProducer producer = session.createProducer(destination);

        // Create messages
        ObjectMessage message = session.createObjectMessage();
        message.setObject(object);
        
        // Send message to queue
        producer.send(message);
    }
}
