package org.springframework.samples.petclinic.api.core.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class InvalidPatchException extends RuntimeException {

    private final JsonNode patch;

    public InvalidPatchException(JsonNode patch, JsonParseException e) {
        super(e);
        this.patch = patch;
    }
}
