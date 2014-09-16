package org.symphodia.common.domain;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

public abstract class AbstractDomainObject implements Serializable {

    public void validate() throws ValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<AbstractDomainObject>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ValidationException();
        }
    }
}
