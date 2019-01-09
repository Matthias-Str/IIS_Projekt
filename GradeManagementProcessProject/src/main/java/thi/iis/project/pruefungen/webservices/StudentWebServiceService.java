/**
 * StudentWebServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface StudentWebServiceService extends javax.xml.rpc.Service {
    public java.lang.String getStudentWebServicePortAddress();

    public thi.iis.project.pruefungen.webservices.StudentWebService getStudentWebServicePort() throws javax.xml.rpc.ServiceException;

    public thi.iis.project.pruefungen.webservices.StudentWebService getStudentWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
