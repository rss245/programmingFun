package com.rss245.sample.exceptions;

// 123
public class NegativesNotAllowedAppException extends Exception {
    private String message = "Negatives are not allowed";
    
    public NegativesNotAllowedAppException() {
        super();
    }
 
    public NegativesNotAllowedAppException(String message) {
        super(message);
        this.message = message;
    }
 
    public NegativesNotAllowedAppException(Throwable cause) {
        super(cause);
    }
}
