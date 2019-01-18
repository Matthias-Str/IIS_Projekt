package thi.iis.project.pruefungen.anmeldung;

import java.io.StringWriter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import thi.iis.project.pruefungen.pojos.InputData;
import thi.iis.project.pruefungen.sender.SendObjectMessageToQueue;

/**
 * Sends ACK if date was persisted
 * 
 * @author Katrin Krüger
 *
 */
public class GetInitDataListener {
    private Session session;
    int counter = 0;

    public GetInitDataListener() {

    }

    public GetInitDataListener(Session session) {
        this.session = session;
    }

    /**
     * start the listener
     * 
     * @throws JMSException
     */
    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("getInitData_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    System.out.println("speichern");
                    // get all necessary date from database
                    FetchInitialDataFromDb fetcher = new FetchInitialDataFromDb();
                    InputData data = fetcher.getData();
                    // send data to queue
                    SendObjectMessageToQueue somtq = new SendObjectMessageToQueue();
                    try {
                        somtq.sendMessage(session, "initData_queue", data);
                        System.out.println("gesendet");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);
    }
}
