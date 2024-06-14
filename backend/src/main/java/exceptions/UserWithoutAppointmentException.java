package exceptions;
/**
 * Indica que el User no tiene el turno asociado
 * @param String message
 */
public class UserWithoutAppointmentException extends Exception {
    public UserWithoutAppointmentException(String message) {
        super(message);
    }
}
