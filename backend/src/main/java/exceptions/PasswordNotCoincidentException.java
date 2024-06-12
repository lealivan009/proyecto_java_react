package exceptions;
/**
 * Indica si las contraseñas de registro de usuario no coinciden
 */
public class PasswordNotCoincidentException extends Exception {
    public PasswordNotCoincidentException(){
        super("Passwords don't match!");
    }
}
