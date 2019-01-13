/**
 * 
 */
package thi.iis.project.pruefungen.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;

/**
 * @author Katrin Krüger
 *
 */
@ApplicationPath("/")
public class RestApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<Class<?>>();
      // add all camunda engine rest resources (or just add those that you actually need).
      classes.addAll(CamundaRestResources.getResourceClasses());

      // mandatory
      classes.addAll(CamundaRestResources.getConfigurationClasses());

      return classes;
    }
}
