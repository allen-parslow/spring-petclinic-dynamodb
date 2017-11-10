package org.springframework.samples.petclinic.api.core.rest;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {
    private final Set<ConstraintViolation<?>> violations;
    private final Object target;

    @SuppressWarnings("unchecked")
    public ValidationException(Object target, Set<? extends ConstraintViolation> violations) {
        this.target = target;
        this.violations = (Set) violations;
    }
}
