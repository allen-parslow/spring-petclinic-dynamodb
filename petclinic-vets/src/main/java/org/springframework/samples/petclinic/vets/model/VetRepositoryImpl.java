package org.springframework.samples.petclinic.vets.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class VetRepositoryImpl implements VetRepository {

    @Override
    public List<Vet> findAll() {
        return asList(new Vet());
    }
}
