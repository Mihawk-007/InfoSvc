package com.aadi.InfoSvc.exception;

import java.util.List;

/**
 * Common exception class for the Info Svc.
 *
 * @author Aditya Kumar
 */
public class InfoSvcException extends Exception {
    private List<String> errorMessages;

    public InfoSvcException(List<String> errorMessages) {
        super();
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
