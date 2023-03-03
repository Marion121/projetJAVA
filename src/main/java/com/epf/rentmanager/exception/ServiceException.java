package com.epf.rentmanager.exception;

public class ServiceException extends Exception {

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(){
        super();
    }
}
