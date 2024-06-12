package exceptions;
/**
 * Indica que las credenciales de username o password no coinciden con las registradas
 */
public class IncorrectUsernameOrPasswordExpection extends Exception{
    public IncorrectUsernameOrPasswordExpection(){
        super("Incorrect email or passwords");
    }
}
