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
package org.springframework.samples.petclinic.visits.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.petclinic.visits.model.Visit;
import org.springframework.samples.petclinic.visits.model.VisitRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Maciej Szarlinski
 * @author Allen Parslow
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class VisitResource {

    private final VisitRepository visitRepository;

    @POST
    @Path("owners/*/pets/{petId}/visits")
    public void create(
        @Valid Visit visit,
        @PathParam("petId") int petId) {

        visit.setPetId(petId);
        log.info("Saving visit {}", visit);
        visitRepository.save(visit);
    }

    @GET
    @Path("owners/*/pets/{petId}/visits")
    public List<Visit> visits(@PathParam("petId") int petId) {
        return visitRepository.findByPetId(petId);
    }
}
