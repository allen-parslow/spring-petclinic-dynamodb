package org.springframework.samples.petclinic.api.core.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.samples.petclinic.api.core.io.SimpleCrudRepository;

public interface RestOperations {
    <T, ID> T patch(ID id, JsonNode patch, SimpleCrudRepository<T, ID> crud);
}
