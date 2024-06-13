package exceptions;

public class AppointmentCancellationException extends Exception {
    public AppointmentCancellationException(String message) {
        super(message);
    }
}
