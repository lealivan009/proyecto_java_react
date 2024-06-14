package exceptions;
/**
 * Indica que el turno debe ser cancelado con 1 dia de anticipacion
 * @param String message
 */
public class AppointmentCancellationException extends Exception {
    public AppointmentCancellationException(String message) {
        super(message);
    }
}
