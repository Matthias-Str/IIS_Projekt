package thi.iis.project.pruefungen.anmeldung;

import java.io.StringReader;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import thi.iis.project.pruefungen.pojos.CurrentDate;
import thi.iis.project.pruefungen.sender.SendTextMessageToQueue;
import thi.iis.project.pruefungen.webservices.DeadlineWebService;
import thi.iis.project.pruefungen.webservices.DeadlineWebServiceProxy;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

public class NewDateListener {
    Session session;

    public NewDateListener() {

    }

    public NewDateListener(Session session) {
        this.session = session;
    }

    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("preparedRegistrationDates_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);
        
        // init new webservices
        final DeadlineWebService deadlineWS = new DeadlineWebServiceProxy().getDeadlineWebService();
        final ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();

        // init sendSendDatePersistedAck
        final SendTextMessageToQueue sdpAck = new SendTextMessageToQueue();

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        // get message text and call persistence function
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        persistDate(deadlineWS, examWS, sdpAck, text);
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };

        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);
    }

    private void persistDate(DeadlineWebService deadlineWS, ExamWebService examWS, SendTextMessageToQueue sdpAck,
            String text) {

        // extract XML to curDate Object
        StringReader reader = new StringReader(text);
        CurrentDate curDate = (CurrentDate) JAXB.unmarshal(reader, CurrentDate.class);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);

        try {
            // persist exam
            if (curDate.getName().contains("ws18")) {
                cal.setTime(sdf.parse(curDate.getDate()));
                examWS.updateExamdate(curDate.getName(), cal);
            }
            // persist deadline
            else {
                cal.setTime(sdf.parse(curDate.getDate()));
                deadlineWS.createDeadline(cal, curDate.getName());
            }
            // send message int datePersistedAck_queue that data was persisted
            sdpAck.sendMessage(session, "datePersistedAck_queue", Calendar.getInstance().getTime().toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
