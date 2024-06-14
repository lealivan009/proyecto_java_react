package exceptions;
/**
 * Indica que algun campo de entrada no cumple con los requisitos solicitados
 * @param String message
 */
public class InvalidFieldException extends Exception {
    public InvalidFieldException(String msg){
        super(msg);
    }
}
