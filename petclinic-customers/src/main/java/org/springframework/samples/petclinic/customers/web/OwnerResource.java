/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.customers.web;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.api.core.rest.RestOperations;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Maciej Szarlinski
 * @author Allen Parslow
 */
@Path("/owners")
@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OwnerResource {

    private final OwnerRepository ownerRepository;
    private final RestOperations restOperations;

    @GET
    @Path("/{id}")
    public Owner findById(@PathParam("id") String id) {
        Owner entity = ownerRepository.read(id);
        if (entity == null) {
            throw new WebApplicationException(404);
        }
        return entity;
    }

    @POST
    public Owner create(@Valid Owner entity) {
        entity.setId(UUID.randomUUID().toString());
        ownerRepository.update(entity);
        return entity;
    }

    @PUT
    @Path("/{id}")
    public Owner update(@PathParam("id") String id, @Valid Owner entity) {
        ownerRepository.update(entity);
        return entity;
    }


    @PATCH
    @Path("/{id}")
    public Owner patch(@PathParam("id") String id, JsonNode patch) {
        return restOperations.patch(id, patch, ownerRepository);
    }

    @GET
    public List<Owner> search(@QueryParam("q") String query) {
        if (!isEmpty(query)) {
            return ownerRepository.findByLastName(query);
        } else {
            return ownerRepository.findOnePage();
        }
    }
}
