package main.java.util;

public class NmmRuntimeException extends RuntimeException {
    public NmmRuntimeException() { super(); }
    public NmmRuntimeException(String message) { super(message); }
    public NmmRuntimeException(String message, Throwable cause) { super(message, cause); }
    public NmmRuntimeException(Throwable cause) { super(cause); }
}