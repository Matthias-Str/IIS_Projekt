/**
 * DeadlineWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface DeadlineWebService extends java.rmi.Remote {
    public thi.iis.project.pruefungen.webservices.Deadline createDeadline(java.util.Calendar arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Deadline selectDeadlineByName(java.lang.String arg0) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Deadline[] selectAll() throws java.rmi.RemoteException;
}
