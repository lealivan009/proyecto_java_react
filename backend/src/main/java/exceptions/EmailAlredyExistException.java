package exceptions;
/**
     * Indica que el email ya existe registrado
     * @param String message
 */
public class EmailAlredyExistException extends Exception {
    public EmailAlredyExistException(String msg){
        super(msg);
    }
}
