package thi.iis.project.pruefungen.servicetasks.documentlistener;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ArchieveFile implements JavaDelegate {

    @Override
    public void execute(DelegateExecution arg0) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new CopyFileRouteBuilder());
        
        context.start();
        Thread.sleep(100);
        
        context.stop();

    }

}
