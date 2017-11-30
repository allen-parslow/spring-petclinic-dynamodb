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
package org.springframework.samples.petclinic.vets.web;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.api.core.rest.RestOperations;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collections;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Maciej Szarlinski
 * @author Allen Parslow
 */
@Path("/vets")
@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VetResource {

    private final VetRepository vetRepository;
    private final RestOperations restOperations;

    @PATCH
    @Path("/{id}")
    public Vet patch(@PathParam("id") String id, JsonNode patch) {
        return restOperations.patch(id, patch, vetRepository);
    }

    @GET
    public List<Vet> search() {
        List<Vet> results = vetRepository.findOnePage();
        Collections.sort(results);
        return results;
    }
}
