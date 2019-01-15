package thi.iis.project.pruefungen.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Helper class for sending textmessage to queue
 * @author Katrin Krüger
 *
 */
public class SendTextMessageToQueue {
    
    public SendTextMessageToQueue(){
        
    }
    
    /**
     * send a message
     * @param session
     * @param subject = queue name
     * @param text
     * @throws JMSException
     */
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
