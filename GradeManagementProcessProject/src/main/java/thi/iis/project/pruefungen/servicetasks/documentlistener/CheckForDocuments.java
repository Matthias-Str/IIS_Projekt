package thi.iis.project.pruefungen.servicetasks.documentlistener;

import java.io.File;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckForDocuments implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        File folder = new File("/home/lars/pruefungen_upload");
        File[] fileList = folder.listFiles();
                
        // check if there is a new file
        if(fileList.length != 0){
            execution.setVariable("msgReceived", true);
            String filename = fileList[0].getName();
            execution.setVariable("fileName", filename);
        }
        else{
            execution.setVariable("msgReceived", true);
        }
        
        
    }

}
