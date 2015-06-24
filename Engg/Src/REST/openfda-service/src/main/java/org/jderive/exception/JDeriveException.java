package org.jderive.exception;

/**
 * Created by Durga on 6/23/2015.
 */
public class JDeriveException extends Exception {

    public JDeriveException(String message) {
        super(message);
    }

    public JDeriveException(String message, Throwable cause) {
        super(message, cause);
    }
}
