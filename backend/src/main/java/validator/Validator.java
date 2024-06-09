package validator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Validator {
    
    @Inject
    jakarta.validation.Validator validator;

    public void validate(Object objToValidate) throws Exception {
        var contrains = validator.validate(objToValidate);
        if (!contrains.isEmpty()) {
            StringBuilder errorsMessage = new StringBuilder();
            contrains.stream()
                    .forEach(c -> errorsMessage.append(c.getMessageTemplate()).append(", "));
            throw new Exception(errorsMessage.toString());
        }
    }
}
