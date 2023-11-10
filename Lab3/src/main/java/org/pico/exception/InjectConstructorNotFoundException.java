package org.pico.exception;

/* Occurs when class has no @Inject constructor, and no default(0-argument) constructor */
public class InjectConstructorNotFoundException extends Exception {
    public InjectConstructorNotFoundException(String message) {
        super(message);
    }
}
