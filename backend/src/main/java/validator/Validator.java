package validator;

import exceptions.InvalidFieldException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Validator {
    
    @Inject
    jakarta.validation.Validator validator;

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
