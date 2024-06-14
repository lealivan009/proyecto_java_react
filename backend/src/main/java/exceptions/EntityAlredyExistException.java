package exceptions;
/**
     * Indica que el email ya existe registrado
     * @param String message
 */
public class EntityAlredyExistException extends Exception {
    public EntityAlredyExistException(String msg){
        super(msg);
    }
}
