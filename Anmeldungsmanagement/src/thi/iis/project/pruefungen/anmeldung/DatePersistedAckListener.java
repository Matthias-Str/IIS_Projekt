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
import thi.iis.project.pruefungen.sender.SendTextMessageToQueue;

/**
 * Sends ACK if date was persisted
 * @author Katrin Krüger
 *
 */
public class DatePersistedAckListener {
    private Session session;
    int counter = 0;

    public DatePersistedAckListener() {

    }

    public DatePersistedAckListener(Session session) {
        this.session = session;
    }

    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("datePersistedAck_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    // new message received -> increment counter
                    counter += 1;
                    // if all 8 messages arrived send message to task
                    if(counter == 8){
                        System.out.println("speichern");
                        // get all necessary date from database
                        FetchInitialDataFromDb fetcher = new FetchInitialDataFromDb();
                        InputData data = fetcher.getData();
                        // convert to xml
                        StringWriter sw = new StringWriter();
                        JAXB.marshal(data, sw);
                        String text = sw.toString();
                        // send data to queue
                        SendTextMessageToQueue somtq = new SendTextMessageToQueue();
                        try {
                            somtq.sendMessage(session, "rawInitData_queue", text);
                            System.out.println("gesendet");
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                        counter = 0;
                    }
                }
            }
        };

        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);
    }
}
