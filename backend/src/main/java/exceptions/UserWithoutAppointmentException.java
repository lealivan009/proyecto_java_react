package exceptions;

public class UserWithoutAppointmentException extends Exception {
    public UserWithoutAppointmentException(String message) {
        super(message);
    }
}
