package thi.iis.project.pruefungen.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SendTextMessageToQueue {
    
    public SendTextMessageToQueue(){
        
    }
    
    public void sendMessage(Session session, String subject, String text) throws JMSException{
        // Create Queue (if it doesn't already exist)
        Destination destination = session.createQueue(subject);

        // Create message producer
        MessageProducer producer = session.createProducer(destination);
        
        // Create messages
        TextMessage message = session.createTextMessage(text);
        
        // Send message to queue
        producer.send(message);
    }

}
