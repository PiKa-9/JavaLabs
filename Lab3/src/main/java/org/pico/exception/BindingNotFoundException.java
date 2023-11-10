package org.pico.exception;

/* Occurs when the class, or constructor argument doesn't have binding */
public class BindingNotFoundException extends Exception {
    public BindingNotFoundException(String message) {
        super(message);
    }
}
