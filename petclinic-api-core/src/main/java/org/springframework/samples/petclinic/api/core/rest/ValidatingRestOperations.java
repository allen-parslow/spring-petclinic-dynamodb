package org.springframework.samples.petclinic.api.core.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.api.core.io.SimpleCrudRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ValidatingRestOperations implements RestOperations {

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public <T, ID> T patch(ID id, JsonNode patch, SimpleCrudRepository<T, ID> crud) {
        T entity = crud.read(id);

        ObjectReader objectReader = objectMapper.readerForUpdating(entity);
        try {
            objectReader.readValue(patch);
        } catch (JsonParseException e) {
            throw new InvalidPatchException(patch, e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ValidationException(entity, violations);
        }

        crud.update(entity);

        return entity;
    }
}
