package thi.iis.project.pruefungen.servicetasks;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

//MHoepp
public class EmailNotification implements JavaDelegate {

    public static final String SENDER_EMAIL_IDENTIFIER = "VirtuellesPruefungsamt";
    public static final String SENDER_LOGIN_PASSWORD = "BpmnCreatesMoreProblemsThanItSolves";   //Me IRL: https://www.youtube.com/watch?v=2H7NZ0GNIIE
    public static final String SMTP_SERVER_ADDRESS = "mail.protonmail.ch";
    public static final int SMTP_PORT = 25;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        Student[] studentlist = studentWS.selectAllStudents();
        
        //List<Student> studentlist = (List<Student>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_STUDENTLIST);
        Map<Student,String> pdfmap = (Map<Student,String>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_PDF_MAP);
        
        for(Student student : studentlist)
        {
            String mailDestination = student.getMail();
            String pdfpath = pdfmap.get(student);
            if( pdfpath == null )
            {
                continue;
            }
            String mailString = "Sehr geehrte/r Herr/Frau "+student.getLastname()+
                    "\n\nIhre Prüfungsergebnisse sind nun verfügbar \n Besuchen Sie das Studentenportal oder sehen Sie das Notenblatt an folgender Adresse ein:\n"+
                    "\n"+pdfpath+"\n"+
                    "\nGrüße,\n"+
                    "Ihr Prüfungsamt (BPMN Simulation)";
            
            
            if(!mailDestination.contains("demo"))
            {
                Properties emailProperties = System.getProperties();
                emailProperties = System.getProperties();
                emailProperties.put("mail.smtp.port", Integer.toString(this.SMTP_PORT));
                emailProperties.put("mail.smtp.auth", "true");
                emailProperties.put("mail.smtp.starttls.enable", "true");
                
                String headerSubject = "Notebekanntgabe";
                
                Session mailSession = Session.getDefaultInstance(emailProperties);
                MimeMessage mimeMessage = new MimeMessage(mailSession);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestination));
                mimeMessage.setSubject(headerSubject);
                mimeMessage.setText(mailString);
                
                //Sending message now:
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(SMTP_SERVER_ADDRESS,SENDER_EMAIL_IDENTIFIER,SENDER_LOGIN_PASSWORD);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                
            }
            
            
        }
    }

}
