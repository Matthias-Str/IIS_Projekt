/**
 * StudentWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface StudentWebService extends java.rmi.Remote {
    public thi.iis.project.pruefungen.webservices.Student selectByRegistrationName(java.lang.String arg0) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Student[] selectAllStudents() throws java.rmi.RemoteException;
}
