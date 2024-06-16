package exceptions;
/**
 * Indica que la especialidad pasada por el cuerpo de la solicitud no existe o no es valida
 *
 */
public class SpecialityNotExistException extends Exception {
    public SpecialityNotExistException(){
        super("Speciality does not exist!");
    }
}
