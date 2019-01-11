package thi.iis.project.pruefungen.sender;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class SendObjectMessageToQueue {

    public SendObjectMessageToQueue() {

    }

    public void sendMessage(Session session, String subject, Map<String, Object> map) throws JMSException {
        // Create Queue (if it doesn't already exist)
        Destination destination = session.createQueue(subject);

        // Create message producer
        MessageProducer producer = session.createProducer(destination);

        // Create messages
        MapMessage message = session.createMapMessage();
        for(Entry<String, Object> e : map.entrySet()){
            message.setObject(e.getKey(), e.getValue());
        }

        // Send message to queue
        producer.send(message);
    }
}
