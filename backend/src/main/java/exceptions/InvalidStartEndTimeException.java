package exceptions;
/**
 * Indica que el horario de inicio y finalizacion de turno no son posibles
 */
public class InvalidStartEndTimeException extends Exception {
    public InvalidStartEndTimeException(){
        super("End time cannot be earlier than start time");
    }
}
