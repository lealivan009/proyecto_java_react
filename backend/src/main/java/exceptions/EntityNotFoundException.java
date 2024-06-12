package exceptions;
/**
 * Indica que no se encuentra la entidad solicitada y arroja la exception
 * @param String message
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String msg){
        super(msg);
    }
    
}
