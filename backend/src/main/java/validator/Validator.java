package validator;

import exceptions.InvalidFieldException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Validator {
    
    @Inject
    jakarta.validation.Validator validator;


    /**
     * Valida los campos de entrada con ayuda de los DTO y las anotaciones indicadas en sus atributos
     * @param objToValidate del tipo Object y es comunmente un DTO
     * @throws InvalidFieldException
     */
    public void validate(Object objToValidate) throws InvalidFieldException {
        var contrains = validator.validate(objToValidate);
        if (!contrains.isEmpty()) {
            StringBuilder errorsMessage = new StringBuilder();
            contrains.stream()
                    .forEach(c -> errorsMessage.append(c.getMessageTemplate()).append(", "));
            throw new InvalidFieldException(errorsMessage.toString());
        }
    }
}
